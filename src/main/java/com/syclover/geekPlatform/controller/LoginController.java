package com.syclover.geekPlatform.controller;

import com.fasterxml.jackson.databind.ext.NioPathDeserializer;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.MailService;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Controller
public class LoginController {

    private final String PlatformUrl = "http://localhost:8080/";

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("bloomFilterServiceImpl")
    private BloomFilterService bloomFilterService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MailService mailService;

    //  返回index模板
    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    //    用户注册控制器
    @PostMapping("/addUser")
    @ResponseBody
    public ResultT addUser(HttpServletRequest request){
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String iscuit = request.getParameter("iscuit");
        String email = request.getParameter("email");

        if (iscuit != null) {
            user.setIsCuit(1);
        }else {
            user.setIsCuit(0);
        }

//        密码为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username) || StringUtils.isEmpty(email)){
            //用户名或者密码或邮箱缺失
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAuthToken(token);

        //检查用户名是否已经注册

        if (bloomFilterService.contain(username)){
            //用户名已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else{

            //发送验证邮件，并将token加入缓存设置过期时间1天
            String content = PlatformUrl + "api/verifytoken?token=" + token;
            mailService.sendSimpleMail(email,"GeekPlatform email check",content);
            //注册用户
            userService.registerUser(user);
            int id = userService.getLastId();
            //分别把用户名和token加入redis缓存
            redisService.setex(RedisUtil.generateEmailToken(token),86400,1);
            redisService.set(RedisUtil.generateUserKey(id),username);
            //加入布隆过滤器缓存
            bloomFilterService.add(username);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }

    //    登陆控制器    模板引擎不需要了这些得改 但还不知道怎么改
    //    改为直接使用邮箱登陆
    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return "index";
        }

        User user = userService.getLoginUser(username).getData();
        if (password.equals(user.getPassword())){
            session.setAttribute("user",username);
            return "redirect:/profile";
        }else{
            return "index";
        }
    }

//    登录后跳转至profile路由
    @GetMapping("/profile")
    public String profile(HttpServletRequest request,HttpSession session){
        if (session.getAttribute("user") == null){
            return "index";
        }else {
            return "profile";
        }
    }

    @RequestMapping("/api/verifytoken")
    @ResponseBody
    public ResultT verifyToken(@Param("token") String token){
        if (token == null){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

        //  token在缓存中过期
        if (redisService.get(RedisUtil.generateEmailToken(token)) == null){
            return new ResultT(ResponseCode.CACHE_EXPIRED.getCode(),ResponseCode.CACHE_EXPIRED.getMsg(),null);
        }

        //激活用户的邮箱
        if (userService.activeEmail(token) != 0){
            //成功
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }else {
            //token错误或数据库失败
            return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
        }
    }


    /*
    * 接口用于重发邮箱验证邮件
    * 用户登陆以后才可以使用接口
    * 接口根据传入的session找到
    * 用户对象，再找到数据库中的email地址*/
    @RequestMapping("/api/resetToken")
    @ResponseBody
    public ResultT activeEmail(HttpSession session){
        String username = (String) session.getAttribute("user");
        if (username == null){
            // session中没有user 用户没有登陆
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }else {
            // 得到user对象 到数据库中查邮箱
            User user = userService.getLoginUser(username).getData();
            String email = user.getEmail();
            // 再次生成token 更新用户数据库和缓存
            String token = UUID.randomUUID().toString().replace("-", "");
            redisService.setex(RedisUtil.generateEmailToken(token),86400,1);
            int id = user.getId();
            // 更新对应用户token
            userService.updateToken(id,token);
            String content = PlatformUrl + "?token=" + token;
            mailService.sendSimpleMail(email,"Reset token test",content);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }
}
