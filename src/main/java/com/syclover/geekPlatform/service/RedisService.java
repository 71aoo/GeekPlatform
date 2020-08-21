package com.syclover.geekPlatform.service;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
public interface RedisService {

    Object get(String key);

    boolean set(String key, Object value);

    boolean setex(String key, long second, Object value);
}
