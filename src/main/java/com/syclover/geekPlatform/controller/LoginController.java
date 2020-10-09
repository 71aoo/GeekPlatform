package com.syclover.geekPlatform.controller;

import com.alibaba.fastjson.JSON;
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
     * @param code
     * @return
     */
    @PostMapping("/addUser")
    public ResultT addUser(String username,String password,String email,String code){
        User user = new User();

        //  密码为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username) || StringUtils.isEmpty(email)){
            //用户名或者密码或邮箱缺失
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

        // 判断用户名长度
        if( !(username.length() <= 12 && username.length() > 4)){
            return new ResultT(ResponseCode.NAME_LENGTH_ERROR.getCode(),ResponseCode.NAME_LENGTH_ERROR.getMsg(),null);
        }

        // 判断密码长度
        if ( !(password.length() >= 5 && password.length() < 16)){
            return new ResultT(ResponseCode.PASSWORD_LENGTH_ERROR.getCode(),ResponseCode.PASSWORD_LENGTH_ERROR.getMsg(),null);
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
        String email_code  = (String) redisService.get(RedisUtil.generateEmailCode(email));
        if (email_code == null){
            return new ResultT(ResponseCode.CACHE_EXPIRED.getCode(),ResponseCode.CACHE_EXPIRED.getMsg(),null);
        }
        if (!email_code.equals(code)){
            return new ResultT(ResponseCode.EMAIL_USED_ERROR.getCode(),ResponseCode.EMAIL_CODE_WRONG.getMsg(),null);
        }

        //检查用户名是否已经注册
        if (bloomFilterService.contain(username)){
            //用户名已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else{
            userService.registerUser(user);
            int id = userService.getLastId();
            redisService.set(RedisUtil.generateUserKey(id),username);
            redisService.set(RedisUtil.generateEmailKey(email),1);
            bloomFilterService.add(username);
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
            return new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),null);
        }
    }

    /**
     * 向用户邮箱发送验证码
     * @param email
     * @return
     */
    @PostMapping("/sendCode")
    public String sendCode(String email){
        if (email == null){
            return JSON.toJSONString(new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null));
        }
        if (redisService.get(RedisUtil.generateEmailKey(email)) != null){
            return JSON.toJSONString(new ResultT(ResponseCode.EMAIL_USED_ERROR.getCode(),ResponseCode.EMAIL_USED_ERROR.getMsg(),null));
        }
        String code = (int)((Math.random()*9+1)*1000)+"";
        String content = "Welcome to 11th GeekChallenge,your email code is " + code;
        if (redisService.get(RedisUtil.generateEmailCode(email)) != null){
            return JSON.toJSONString(new ResultT(ResponseCode.CODE_NOT_EXPIRED.getCode(),ResponseCode.CODE_NOT_EXPIRED.getMsg(),null));
        }
        mailService.sendSimpleMail(email,"Geek 11th Email code verify",content);
        redisService.setex(RedisUtil.generateEmailCode(email),360,code);
        return JSON.toJSONString(new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null));
    }


}
