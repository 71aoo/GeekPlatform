package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.Student;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.BCPEUtils;
import com.syclover.geekPlatform.util.CleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //    根据id得到用户对象
    @Override
    public ResultT<User> getUser(int id) {
        User user = userMapper.getUserById(id);
        if (user != null) {
            user = CleanUtil.cleanUser(user);
            ResultT<User> result = new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), user);
            return result;
        }else{
            return new ResultT<>(ResponseCode.USER_NOT_FOUND.getCode(),ResponseCode.USER_NOT_FOUND.getMsg(),null );
        }
    }

    @Override
    public int registerUser(User user) {
        String rawPwd = user.getPassword();
        user.setPassword(BCPEUtils.encode(rawPwd));
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
    public int getTeamId(int id) {
        User user = userMapper.getUserById(id);
        return user.getTeamId();
    }

    @Override
    public int getTeamId(User user) {
        return user.getTeamId();
    }

    @Override
    public int updateTeam(int teamId, int id) {
        int result = userMapper.updateTeam(teamId, id);
        return result;
    }

    @Override
    public int getLastId() {
        User user = userMapper.getLastUserId();
        return user.getId();
    }

    @Override
    public int activeEmail(String token) {
        return userMapper.activeEmail(token);
    }

    @Override
    public int updateToken(int id, String token) {
        return userMapper.updateToken(id,token);
    }

    @Override
    public int updateProfile(User user) {
        return userMapper.updateAll(user);
    }

    @Override
    public User getByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public MyUserBean getUserBeanByEmail(String name) {
        return userMapper.getUserBeanByEmail(name);
    }

    @Override
    public Student getStudent(String name, String number) {
      return userMapper.getStudent(name,number);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userMapper.getAllUser();
        List<User> cleanUser = new ArrayList<>();
        for (User user : allUser){
            user = CleanUtil.cleanUser(user);
            cleanUser.add(user);
        }
        return cleanUser;
    }

    @Override
    public User getTeamUser(int id) {
        User teamUser = userMapper.getTeamUser(id);
        CleanUtil.cleanUser(teamUser);
        return teamUser;
    }


}
