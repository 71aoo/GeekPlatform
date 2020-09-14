package com.syclover.geekPlatform.config;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author cueyu
 * @Date 2020/9/14
 */

@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultT result = new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        httpServletResponse.getWriter().write(JSON.toJSONString(result.toString()));
    }
}
