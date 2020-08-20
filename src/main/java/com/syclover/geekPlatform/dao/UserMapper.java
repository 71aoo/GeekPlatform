package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int addUser(User user);

    User getUserByUsername(String username);

    User getUserById(long id);

}
