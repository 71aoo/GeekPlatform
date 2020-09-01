package com.syclover.geekPlatform.service;


public interface BloomFilterService {

    void initBloomFilter();

    // 判断是否存在字符串
    boolean contain(String name);

    // 添加布隆过滤器的值
    void add(String name);

}
