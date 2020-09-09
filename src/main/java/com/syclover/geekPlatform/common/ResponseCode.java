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
    TEAM_JOIN_FAILED(801,"TEAM JOINED FAILED"),
    TEAM_JOIN_SUCCESS(802,"TEAM JOINED SUCCEESS"),
    NAME_HAVE_ERROR(803,"NAME ALREADY HAVE"), // 名称已被使用
    PARAMETER_MISS_ERROR(804,"PARAMETER MISS"),
    CACHE_EXPIRED(805,"CACHE EXPIRED"),// 缓存过期
    LOGIN_FIRST_ERROR(806,"USER HAVENT LOGIN"); // 用户未登陆或登陆过期


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
