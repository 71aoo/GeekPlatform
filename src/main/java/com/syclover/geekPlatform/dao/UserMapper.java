package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int addUser(@Param("user") User user);

    User getUserByUsername(String username);

    User getUserById(int id);

    int updateTeam(int id,long teamid);

    //拿取最新的id用于生成redis键
    User getLastUserId();

    //激活邮箱
    int activeEmail(String token);
}
