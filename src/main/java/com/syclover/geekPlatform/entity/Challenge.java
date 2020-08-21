package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @Author: Playwi0
 * @Data: 2020/8/14
 */
public class Challenge {

    // 主键
    private int id;
    // 名称
    private String name;
    // 简介
    private String intro;
    // 提示
    private String hint;
    // 分数
    private int score;
    // 作者
    private Author author;
    // 分类
    private Category category;
    // 隐藏
    private int hidden;
    // 是否有解
    private int firstBlood;
    // 逻辑删除
    private int isDel;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public int getFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(int firstBlood) {
        this.firstBlood = firstBlood;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
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
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", hint='" + hint + '\'' +
                ", score=" + score +
                ", author=" + author +
                ", category=" + category +
                ", hidden=" + hidden +
                ", firstBlood=" + firstBlood +
                ", isDel=" + isDel +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
