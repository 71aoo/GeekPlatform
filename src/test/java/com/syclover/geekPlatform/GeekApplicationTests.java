package com.syclover.geekPlatform;

import com.syclover.geekPlatform.mapper.UserMapper;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeekApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {

        User byId = userMapper.getById(102);
        System.out.println(byId);
    }

}
