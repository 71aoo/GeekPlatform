package com.syclover.geekPlatform.config.spring;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import org.springframework.security.access.AccessDeniedException;
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
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultT result = new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(),null);

        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
