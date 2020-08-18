package com.syclover.geekPlatform;

import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class GeekApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(GeekApplicationTests.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        User user = new User();
        user.setId(1);
        user.setName("dsdsd");
        redisTemplate.opsForValue().set("ojbk", user);
//        String ok = (String)redisTemplate.opsForValue().get("ok");
//        System.out.println(ok);
//        logger.info("dasdasdadasdasdas");
    }

    @Test
    void Loads() {

        User user = userMapper.getUserById(105);
        System.out.println(user);
    }

}
