package com.syclover.geekPlatform.common;

import java.io.Serializable;

/**
 * @Author: Playwi0
 * @Data: 2020/8/11
 *
 * 业务数据或服务响应数据类
 */
public class ResultT<T> implements Serializable{

    private static final long serialVersionUID = -399311564206921895L;

    public final static int SUCCESS_CODE = 200;
    public final static int ERROR_CODE = 500;

    public final static ResultT SUCCESS = new ResultT(SUCCESS_CODE, null, null);
    public final static ResultT ERROR = new ResultT(ERROR_CODE, null, null);

    private int status;
    private String msg;
    private T data;

    public ResultT() {
    }

    public ResultT(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultT{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
