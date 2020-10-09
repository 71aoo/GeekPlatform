package com.syclover.geekPlatform.config.spring;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @Author cueyu
 * @Date 2020/9/14
 */

@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException, NoSuchElementException {
        try{
            // 改掉了返回login success
            httpServletResponse.getWriter().write(JSON.toJSONString(new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), null)));
        }catch (Exception e){
            httpServletResponse.getWriter().write(JSON.toJSONString(new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null)));
        }
    }
}
