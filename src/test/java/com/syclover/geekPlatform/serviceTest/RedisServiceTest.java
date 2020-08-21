package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void set(){

        User user = userMapper.getUserById(1);

        String key = RedisUtil.generateUserKey(user.getId());

        boolean set = redisService.set(key, user);

        System.out.println(set);


    }

    @Test
    public void get(){

        String s = RedisUtil.generateUserKey(1);

        User o = (User)redisService.get(s);

        System.out.println(o);
    }
}
