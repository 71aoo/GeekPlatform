package com.syclover.geekPlatform.util;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
public class RedisUtil {

    private final static String TEAM_PREFIX = "TEAM_INFO_";
    private final static String USER_PREFIX = "USER_INFO_";
    private final static String EMAIL_TOKEN_PREFIX = "EMAIL_TOKEN_";


    /*
    * 生成 redis 存储队伍信息的 key
    * */
    public static String generateTeamKey(int id){

        return TEAM_PREFIX + id;
    }

    /*
     * 生成 redis 存储用户信息的 key
     * */
    public static String generateUserKey(int id){

        return USER_PREFIX + id;
    }

    //生成 redis储存邮件token过期信息的key
    public static String generateEmailToken(String token) {
        return EMAIL_TOKEN_PREFIX + token;
    }

}
