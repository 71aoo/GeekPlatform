package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */
public class Team {

    // 主键
    private int id;

    // 名称
    private String name;

    // token
    private String token;

    // 图片地址
    private String headerImg;

    // 个性签名
    private String motto;

    // 队伍成员
    private User memberOne;

    // 队伍成员
    private User memberTwo;

    // 得分
    private int points;

    // 是否本校
    private int isCuit;

    // 逻辑删除
    private int isDel;

    // 最后得分时间
    private Timestamp lastPointsTime;

    // 创建时间
    private Timestamp createdTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public User getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(User memberOne) {
        this.memberOne = memberOne;
    }

    public User getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(User memberTwo) {
        this.memberTwo = memberTwo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getIsCuit() {
        return isCuit;
    }

    public void setIsCuit(int isCuit) {
        this.isCuit = isCuit;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public Timestamp getLastPointsTime() {
        return lastPointsTime;
    }

    public void setLastPointsTime(Timestamp lastPointsTime) {
        this.lastPointsTime = lastPointsTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", motto='" + motto + '\'' +
                ", memberOne=" + memberOne +
                ", memberTwo=" + memberTwo +
                ", points=" + points +
                ", isCuit=" + isCuit +
                ", isDel=" + isDel +
                ", lastPointsTime=" + lastPointsTime +
                ", createdTime=" + createdTime +
                '}';
    }
}
