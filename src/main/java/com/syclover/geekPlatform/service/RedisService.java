package com.syclover.geekPlatform.service;

import java.util.Set;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
public interface RedisService {

    Object get(String key);

    boolean set(String key, Object value);

    boolean setex(String key, long second, Object value);

    //拿取所有的keys
    Set<String> getAllKeys();

    //拿取所有TEAM_INFO的keys
    Set<String> getTeamKeys();

    //拿取所有USER_INFO的keys
    Set<String> getUserKeys();

    //拿取所有EMAIL_TOKEN的keys
    Set<String> getEmailTokens();

    Set<String> getAnnouncementsKeys();
}
