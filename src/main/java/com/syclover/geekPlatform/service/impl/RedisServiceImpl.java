package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /*
    * 获取
    * */
    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) {

        try {
            redisTemplate.opsForValue().set(key, value);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setex(String key, long second, Object value) {

        try {
            redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<String> getAllKeys() {
        Set<String> keys = redisTemplate.keys("*");
        return keys;
    }

    @Override
    public Set<String> getUserKeys() {
        Set<String> keys = redisTemplate.keys("USER_INFO_*");
        return keys;
    }

    @Override
    public Set<String> getTeamKeys() {
        Set<String> keys = redisTemplate.keys("TEAM_INFO_*");
        return keys;
    }
}
