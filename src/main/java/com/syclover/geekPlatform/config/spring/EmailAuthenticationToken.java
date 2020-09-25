package com.syclover.geekPlatform.config.spring;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @Author cueyu
 * @Date 2020/9/22
 */
public class EmailAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials; // 认证，密码字段

    public EmailAuthenticationToken(Object principal,Object credentials){
        super((Collection)null);
        this.credentials = credentials;
        this.principal = principal;
        setAuthenticated(false);
    }

    public EmailAuthenticationToken(Collection<? extends GrantedAuthority> authorities,Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }
}
