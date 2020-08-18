package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @Author: Playwi0
 * @Data: 2020/8/18
 */
public class Solve {

    // 主键
    private int id;

    // 所属队伍
    private Team team;

    // 队伍token
    private String token;

    // 用户
    private User user;

    // 提交flag
    private String flag;

    // 创建时间
    private Timestamp createdTime;

    // 修改时间
    private Timestamp updatedTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
        return "Solve{" +
                "id=" + id +
                ", team=" + team +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", flag='" + flag + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
