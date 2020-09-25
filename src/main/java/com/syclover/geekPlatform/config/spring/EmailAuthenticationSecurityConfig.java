package com.syclover.geekPlatform.config.spring;

import com.syclover.geekPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @Author cueyu
 * @Date 2020/9/23
 */

@Component
public class EmailAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserService userService;

    public void configure(HttpSecurity http) throws Exception{
        super.configure(http);
        EmailAuthenticationFilter filter = new EmailAuthenticationFilter(new AntPathRequestMatcher("/login/email", "POST"));
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);

        EmailAuthenticationProvider provider = new EmailAuthenticationProvider(userService);
        http.authenticationProvider(provider)
                .addFilterAfter(filter,UsernamePasswordAuthenticationFilter.class);

    }
}
