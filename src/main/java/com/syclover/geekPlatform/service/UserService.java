package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Student;
import com.syclover.geekPlatform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
public interface UserService {

    ResultT<User> getUser(int id);

    int registerUser(User user);

    ResultT<User> getLoginUser(String username);

    int getTeamId(int id);

    int getTeamId(User user);

    int updateTeam(int teamId,int id);

    //获取最后的id
    int getLastId();

    // 验证激活邮箱
    int activeEmail(String token);

    //更新token
    int updateToken(int id,String token);

    // 更新用户个人资料
    int updateProfile(User user);

    User getByEmail(String email);

    MyUserBean getUserBeanByEmail(String name);

    Student getStudent(String name,String number);

    List<User> getAllUser();

    User getTeamUser(int id);

    int registerEmail(String email,String token);

    int authEmail(String token);
}
