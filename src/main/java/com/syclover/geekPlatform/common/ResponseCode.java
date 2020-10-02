package com.syclover.geekPlatform.common;

/**
 * @Author: Playwi0
 * @Data: 2020/8/11
 *
 * 快速响应实体
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    FAIL(601, "FAIL"),
    ERROR(602, "ERROR"),
    PARAMETER_ERROR(603, "PARAMETER ERROR"),
    CHALLENGE_HAS_BE_SOLVED(705, "YOU TEAM HAS SOLVE THE CHALLENGE"),
    USER_NOT_IN_TEAM(704, "THE USER IS NOT IN THE TEAM"),
    TEAM_JOIN_FAILED(801,"TEAM JOINED FAILED"),
    TEAM_JOIN_SUCCESS(802,"TEAM JOINED SUCCEESS"),
    NAME_HAVE_ERROR(803,"NAME ALREADY HAVE"), // 名称已被使用
    PARAMETER_MISS_ERROR(804,"PARAMETER MISS"),
    CACHE_EXPIRED(805,"CACHE EXPIRED"),// 缓存过期
    LOGIN_FIRST_ERROR(806,"USER HAVENT LOGIN"), // 用户未登陆或登陆过期
    EMAIL_USED_ERROR(807,"EMAIL HAS BEEN USED"), // 邮箱已被注册
    STUDENT_NUMBER_USED_ERROR(808,"STUDENT NUMBER HAS BEEN USED"), // 学号已被注册
    TEAM_NOT_FOUND(809,"TEAM NOT FOUND"),
    TEAM_NAME_USED(900,"TEAM NAME HAS BEEN USED"),
    USER_HAS_IN_TEAM(901,"USER HAS IN TEAM"),
    USER_NAME_VALID(902,"USER NAME VALID"),
    TEAM_NAME_VALID(903,"TEAM NAME VALID"),
    USER_NOT_FOUND(904,"USER NOT FOUND");

    // 响应码
    private final int code;
    // 响应提示
    private final String msg;

    ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
