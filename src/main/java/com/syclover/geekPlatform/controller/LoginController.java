package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //  返回index模板
    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    @Autowired
    private UserService userService;


    //    用户注册控制器
//    还需要用户名不重复逻辑
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
            return "password or username cannot be null";
        }
        user.setUsername(username);
        user.setPassword(password);
        //检查用户名是否已经注册
        if (userService.getLoginUser(username).getStatus() == 500) {
            int result = userService.registerUser(user);
            if (result == 1) {
                return "register succeed";
            } else {
                return "something wrong";
            }
        }else{
            return "username already been used!";
        }
    }

    //    登陆控制器
    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return "请输入用户名和密码！";
        }

        User user = userService.getLoginUser(username).getData();
        if (password.equals(user.getPassword())){
            session.setAttribute("user",username);
            return "login succeed";
        }else{
            return "login failed";
        }
    }

}
