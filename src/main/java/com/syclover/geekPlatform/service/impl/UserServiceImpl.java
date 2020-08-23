package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
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
        User user = userMapper.getUserById(id);
        if (user != null) {
            ResultT<User> result = new ResultT<User>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), user);
            return result;
        }else{
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null );
        }
    }

    @Override
    public int registerUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public ResultT<User> getLoginUser(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
             return new ResultT<User>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), user);
        }else{
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null );
        }
    }

    @Override
    public int getTeamId(long id) {
        User user = userMapper.getUserById(id);
        return user.getTeamId();
    }

    @Override
    public int getTeamId(User user) {
        return user.getTeamId();
    }

    @Override
    public int updateTeam(long id, long teamid) {
        int result = userMapper.updateTeam(id, teamid);
        return result;
    }


}
