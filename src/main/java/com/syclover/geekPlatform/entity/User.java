package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @Author: Playwi0
 * @Data: 2020/8/11
 */
public class User {

    // 主键id
    private int id;
    // 真实姓名
    private String realName;
    // 学号
    private String studentId;
    // 是否为本校
    private int isCuit;
    // 个性签名
    private String motto;
    // 头像地址
    private String headerImg;
    // 逻辑删除
    private int isDel;
    // 队伍ID
    private int teamId;
    // 创建时间
    private Timestamp createdTime;
    // 更新时间
    private Timestamp updatedTime;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getIsCuit() {
        return isCuit;
    }

    public void setIsCuit(int isCuit) {
        this.isCuit = isCuit;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", isCuit=" + isCuit +
                ", motto='" + motto + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", isDel=" + isDel +
                ", teamId=" + teamId +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
