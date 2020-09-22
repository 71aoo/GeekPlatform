package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author cueyu
 * @Date 2020/9/20
 */


@RestController
@RequestMapping("/api/user/profile")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ResultT getProfile(HttpSession session,String motto,int is_cuit,String header_img){
        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (is_cuit == 0){
            user.setIsCuit(1);
        }else {
            user.setIsCuit(0);
        }

        user.setHeaderImg(header_img);
        user.setMotto(motto);

        if ( userService.updateProfile(user) == 0){
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }

    @GetMapping("/getdata")
    public ResultT getData(HttpSession session){
        String username = SessionGetterUtil.getUsername(session);
        System.out.println(username);
        User user = userService.getLoginUser(username).getData();
        user.setPassword(null);
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),user);
    }

}
