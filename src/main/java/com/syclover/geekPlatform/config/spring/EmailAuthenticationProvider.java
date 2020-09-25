package com.syclover.geekPlatform.config.spring;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author cueyu
 * @Date 2020/9/23
 */

public class EmailAuthenticationProvider implements AuthenticationProvider {

    UserService userService;

    public EmailAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken token = (EmailAuthenticationToken) authentication;
        MyUserBean user = userService.getUserBeanByEmail((String) authentication.getPrincipal());
        String password = (String) authentication.getCredentials();
        System.out.println("password:" + password);
        if (!password.equals(user.getPassword())){
            return null;
        }
        System.out.println("token principal: " + token.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("cannot find any user");
        }
        System.out.println("provider print: "+ user.getAuthorities());
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
        result.setDetails(token.getDetails());
        return result;
    }

    @Override
    /*关键
    * supports方法用于将provider
    * 和对应要分发的token的class进行绑定 这样provider manager才会将特定的token分发给provider作验证*/
    public boolean supports(Class<?> aClass) {
        return EmailAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
