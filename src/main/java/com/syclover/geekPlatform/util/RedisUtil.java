package com.syclover.geekPlatform.util;

/**
 * @Author: Playwi0
 * @Data: 2020/8/21
 */
public class RedisUtil {

    private final static String TEAM_PREFIX = "TEAM_INFO_";
    private final static String USER_PREFIX = "USER_INFO_";
    private final static String EMAIL_TOKEN_PREFIX = "EMAIL_TOKEN_";
    private final static String ANNOUNCEMENT_PREFIX = "ANNOUNCEMENT_";
    private final static String EMAIL_PREFIX = "EMAIL_INFO_";
    private final static String STUDENT_PREFIX = "STUDENT_";
    private final static String EMAIL_CODE = "EMAIL_CODE_";
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

    public static String generateAnnouncementKey(int id){
        return ANNOUNCEMENT_PREFIX + id;
    }

    public static String generateEmailKey(String name){
        return EMAIL_PREFIX + name;
    }

    public static String generateStudentKey(String number){
        return STUDENT_PREFIX + number;
    }

    public static String generateEmailCode(String email) { return EMAIL_CODE + email;}
}
