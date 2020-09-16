package com.syclover.geekPlatform.entity;

/**
 * @Author cueyu
 * @Date 2020/9/15
 */
public class Announcement {

    private int id;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
