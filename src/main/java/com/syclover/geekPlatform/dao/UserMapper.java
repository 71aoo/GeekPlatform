package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.entity.Student;
import com.syclover.geekPlatform.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int addUser(@Param("user") User user);

    User getUserByUsername(String username);

    User getUserById(int id);

    int updateTeam(@Param("teamId") int teamId,@Param("id") int id);

    //拿取最新的id用于生成redis键
    User getLastUserId();

    //激活邮箱
    int activeEmail(String token);

    //更新对应id用户的token
    int updateToken(int id,String token);

    MyUserBean getUserBeanByUsername(String username);

    int updateAll(@Param("user") User user);

    User getUserByEmail(String email);

    MyUserBean getUserBeanByEmail(String email);

    Student getStudent(String name,String number);

    List<User> getAllUser();

    User getTeamUser(int id);

    int registerEmail(String email,String token);

    int updatePass(@Param("user") User user);

    User isEmailExist(String email);
}
