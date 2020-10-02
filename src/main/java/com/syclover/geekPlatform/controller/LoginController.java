package com.syclover.geekPlatform.controller;

import com.fasterxml.jackson.databind.ext.NioPathDeserializer;
import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.MailService;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.EvaluationException;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
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

    /** 用户注册控制器
     * @param username
     * @param password
     * @param email
     * @return
     */
    @PostMapping("/addUser")
    public ResultT addUser(String username,String password,String email){
        User user = new User();

        //  密码为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username) || StringUtils.isEmpty(email)){
            //用户名或者密码或邮箱缺失
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

        // 判断用户名长度
        if( !(username.length() <= 12 && username.length() > 4)){
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }

        // 判断密码长度
        if ( !(password.length() >= 5 && password.length() < 16)){
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }

        // 判断邮箱是否注册
        if (redisService.get(RedisUtil.generateEmailKey(email)) != null){
            return new ResultT(ResponseCode.EMAIL_USED_ERROR.getCode(),ResponseCode.EMAIL_USED_ERROR.getMsg(),null);
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
            userService.registerUser(user);
            int id = userService.getLastId();
            //发送验证邮件，并将token加入缓存设置过期时间1天
            String content = PlatformUrl + "api/verifytoken?token=" + token;
            mailService.sendSimpleMail(email,"GeekPlatform email check",content);
            //分别把用户名和token以及email加入redis缓存
            redisService.setex(RedisUtil.generateEmailToken(token),86400,1);
            redisService.set(RedisUtil.generateUserKey(id),username);
            redisService.set(RedisUtil.generateEmailKey(email),1);
            bloomFilterService.add(username);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }


    @RequestMapping("/api/verifyToken")
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


    /**
    * 接口用于重发邮箱验证邮件
    * 用户登陆以后才可以使用接口
    * 接口根据传入的session找到
    * 用户对象，再找到数据库中的email地址*/
    @RequestMapping("/resetToken")
    public ResultT activeEmail(HttpSession session) throws Exception{
        String username = SessionGetterUtil.getUsername(session);
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

    /**
     * 检查用户名是否重复接口
     * @param name
     * @return
     */
    @PostMapping("/checkUserName")
    public ResultT checkName(String name){
        if (StringUtils.isEmpty(name)){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        if (bloomFilterService.contain(name)){
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.USER_NAME_VALID.getCode(), ResponseCode.USER_NAME_VALID.getMsg(),null);
        }
    }

}
