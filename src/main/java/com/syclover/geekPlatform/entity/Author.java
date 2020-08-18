package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @Author: Playwi0
 * @Data: 2020/8/14
 */
public class Author {

    private int id;

    private String name;

    private String headerImg;

    private String motto;

    private int challengeCount;

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

    public int getChallengeCount() {
        return challengeCount;
    }

    public void setChallengeCount(int challengeCount) {
        this.challengeCount = challengeCount;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", motto='" + motto + '\'' +
                ", challengeCount=" + challengeCount +
                ", createdTime=" + createdTime +
                '}';
    }
}
