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
    NAME_LENGTH_ERROR(802,"NAME LENGTH ERROR"),
    NAME_HAVE_ERROR(803,"NAME ALREADY HAVE"), // 名称已被使用
    PARAMETER_MISS_ERROR(804,"PARAMETER MISS"),
    CACHE_EXPIRED(805,"CACHE EXPIRED"),// 缓存过期
    LOGIN_FIRST_ERROR(806,"USER HAVENT LOGIN"), // 用户未登陆或登陆过期
    EMAIL_USED_ERROR(807,"EMAIL HAS BEEN USED"), // 邮箱已被注册
    STUDENT_NUMBER_USED_ERROR(808,"STUDENT NUMBER HAS BEEN USED"), // 学号已被注册
    TEAM_NOT_FOUND(809,"TEAM NOT FOUND"),
    TEAM_NAME_USED(900,"TEAM NAME HAS BEEN USED"),
    USER_HAS_IN_TEAM(901,"USER HAS IN TEAM"),
    PASSWORD_LENGTH_ERROR(902,"PASSWORD LENGTH ERROR,SHOULD BE IN 5,16"),
    TEAM_NAME_VALID(903,"TEAM NAME VALID"),
    USER_NOT_FOUND(904,"USER NOT FOUND"),
    USER_IS_STUDENT(905,"USER IS ALREADY A CUIT-STUDENT"),
    LOGIN_FAILED(906,"ACCOUNT OR PASSWORD ERROR"),
    TEAM_UPDATE_ERROR(907,"ERROR OCCURS WHEN UPDATING TEAM"),
    TEAM_NOT_CUIT(908,"TEAM IS NOT A CUIT TEAM"),
    CREATE_TEAM_FIRST(909,"YOU NEED CREATE A TEAM FIRST");

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
