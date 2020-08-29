package com.syclover.geekPlatform.entity;

/**
 * @Author: Playwi0
 * @Data: 2020/8/14
 */
public class Category {

    // 主键
    private int id;
    // 名称
    private String name;
    // 分类下的题目数量
    private int count;

    public Category() {
    }

    public Category(int id) {
        this.id = id;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", count=" + count +
                '}';
    }
}
