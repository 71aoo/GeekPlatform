package com.syclover.geekPlatform;

import com.syclover.geekPlatform.mapper.UserMapper;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest

class GeekApplicationTests {
//
//    @Autowired
//    UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(GeekApplicationTests.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

//        redisTemplate.opsForValue().set("ojbk", "dadasddsds");
//        String ok = (String)redisTemplate.opsForValue().get("ok");
//        System.out.println(ok);
        logger.info("dasdasdadasdasdas");
    }

}
