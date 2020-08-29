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
    USER_NOT_IN_TEAM(704, "THE USER IS NOT IN THE TEAM");


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
