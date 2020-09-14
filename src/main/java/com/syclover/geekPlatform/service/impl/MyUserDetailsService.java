package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author cueyu
 * @Date 2020/9/11
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserBean userBean = userMapper.getUserBeanByUsername(username);
        if (userBean == null){
            throw new UsernameNotFoundException("没有找到用户");
        }
        return userBean;
    }
}
