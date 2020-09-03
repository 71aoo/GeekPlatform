package com.syclover.geekPlatform.controller;

import com.fasterxml.jackson.databind.ext.NioPathDeserializer;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
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

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("bloomFilterServiceImpl")
    private BloomFilterService bloomFilterService;

    @Autowired
    private RedisService redisService;

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

        if (iscuit != null) {
            user.setIsCuit(1);
        }else {
            user.setIsCuit(0);
        }

//        密码为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username)){
            //用户名或者密码缺失
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        user.setUsername(username);
        user.setPassword(password);

        //检查用户名是否已经注册

        if (bloomFilterService.contain(username)){
            //用户名已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else{

            //注册成功
            System.out.println("test");
            userService.registerUser(user);
            int id = userService.getLastId();
            redisService.set(RedisUtil.generateUserKey(id),username);
            bloomFilterService.add(username);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }

    //    登陆控制器    模板引擎不需要了这些得改 但还不知道怎么改
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
}
