package com.syclover.geekPlatform.config.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author cueyu
 * @Date 2020/9/23
 */

public class EmailAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    protected EmailAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected EmailAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(new AntPathRequestMatcher("/login/email","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        HttpSession session = request.getSession();
        // 拿到email,password参数
        String email = request.getParameter("email");
        String password=  request.getParameter("password");
        if (email == null){
            email = "";
        }
        email = email.trim();
        System.out.println("email:" + email);
        EmailAuthenticationToken token = new EmailAuthenticationToken(email,password);
        this.setDetails(request,token);
        // 交给manager去验证
        return this.getAuthenticationManager().authenticate(token);

    }

    public void setDetails(HttpServletRequest request , EmailAuthenticationToken token ){
        token.setDetails(this.authenticationDetailsSource.buildDetails(request ));
    }
}
