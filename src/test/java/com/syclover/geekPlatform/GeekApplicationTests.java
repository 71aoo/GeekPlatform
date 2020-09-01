package com.syclover.geekPlatform;

import com.syclover.geekPlatform.dao.UserMapper;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.util.RedisUtil;
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

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {

        User user = new User();
        user.setId(1);
        redisTemplate.opsForValue().set("ojbk", user);
//        String ok = (String)redisTemplate.opsForValue().get("ok");
//        System.out.println(ok);
//        logger.info("dasdasdadasdasdas");
    }

    @Test
    void Loads() {

        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    void get(){
        Object o = redisService.get("1");
        System.out.println(o);
    }

    @Test
    void set(){
        String id = RedisUtil.generateUserKey(1);
        boolean result = redisService.set(id, "admin");
        System.out.println(result);
    }

}
