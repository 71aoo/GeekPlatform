package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.mapper.UserMapper;
import com.syclover.geekPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultT<User> getUser(long id) {

        User user = userMapper.getById(id);
        if (user == null){
            return ResultT.ERROR;
        }

        return new ResultT<User>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), user);
    }
}
