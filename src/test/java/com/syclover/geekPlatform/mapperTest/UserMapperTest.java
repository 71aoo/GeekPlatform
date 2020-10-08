package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.bean.MyUserBean;
import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.ArrayList;
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

        User user = userMapper.getUserById(11);
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

    @Test
    void getid(){
        User user = userMapper.getLastUserId();
        System.out.println(user.getId());
    }

    @Test
    void addUser(){
        User user = new User();
        user.setUsername("tttt");
        user.setPassword("aaaa");
        int result = userMapper.addUser(user);
        System.out.println(result);
    }

    @Test
    void beanTest(){
        MyUserBean userBean = userMapper.getUserBeanByUsername("admin");
        System.out.println(userBean);
    }

    @Test
    void updateTeam(){
        int id = 11;
        int teamId = 9;
        userMapper.updateTeam(teamId,id);
    }

    @Test
    void getAllTest(){
        List<User> allUser = userMapper.getAllUser();
        List<User> cleanUsers = new ArrayList<>();
        for (User user : allUser){
            user.setPassword(null);
            cleanUsers.add(user);
        }
        System.out.println(allUser);
    }

    @Test
    void getTeamUser(){
        User teamUser = userMapper.getTeamUser(25);
        System.out.println(teamUser);
    }

    @Test
    void emailTest(){
        int result = userMapper.registerEmail("testmail@xx.com","123121");
    }
}
