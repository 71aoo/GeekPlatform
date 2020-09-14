package com.syclover.geekPlatform.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/9/13
 */

/*
* spring security 验证登陆的用户信息类，从数据库中得到
* 只需要user表的username,password以外的id_del,is_cuit,team_id字段
* 即可以用来判断用户对应角色*/

@Component
public class MyUserBean implements UserDetails {
    private String username;
    private String password;
    private int teamId;
    private int isCuit;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        // 登陆成功赋予USER权限
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        // 如果用户teamid为0 表示用户没有队伍 否则赋予TEAM权限
        if (teamId != 0){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("TEAM"));
        }
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MyUserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", teamId=" + teamId +
                ", isCuit=" + isCuit +
                '}';
    }
}
