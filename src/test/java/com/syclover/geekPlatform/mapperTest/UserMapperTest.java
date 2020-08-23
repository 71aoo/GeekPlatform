package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/15
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByIdTest(){

        User user = userMapper.getUserById(105);
        System.out.println(user);
    }

    @Test
    public void getAllUser(){
//
//        List<User> allUser = userMapper.getAllUser();
//        for (User user : allUser){
//            System.out.println(user);
//        }

    }

    @Test
    public void getTeamId(){
        int teamId = userMapper.getUserById(1).getTeamId();
        System.out.println(teamId);
    }
}
