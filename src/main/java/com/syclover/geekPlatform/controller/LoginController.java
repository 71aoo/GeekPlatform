package com.syclover.geekPlatform.controller;

import com.fasterxml.jackson.databind.ext.NioPathDeserializer;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addUser(HttpServletRequest request){
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
            return "请输入用户名和密码";
        }
        user.setUsername(username);
        user.setPassword(password);
        //检查用户名是否已经注册
        if (bloomFilterService.contain(username)){
            return "用户名已被注册！";
        }else{
            System.out.println("test");
            userService.registerUser(user);
            int id = userService.getLastId();
            redisService.set(RedisUtil.generateUserKey(id),username);
            bloomFilterService.add(username);
            return "成功注册";
        }
    }

    //    登陆控制器
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
