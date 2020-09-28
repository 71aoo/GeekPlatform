package com.syclover.geekPlatform.entity;

/**
 * @Author cueyu
 * @Date 2020/9/28
 */
public class Student {
    // 学号
    private int number;
    // 姓名
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
