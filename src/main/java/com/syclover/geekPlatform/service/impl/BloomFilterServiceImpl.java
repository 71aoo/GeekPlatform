package com.syclover.geekPlatform.service.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/8/29
 */

@Service
public class BloomFilterServiceImpl implements BloomFilterService {

    private BloomFilter<String> bf;

    @Autowired
    private RedisService redisService;

    /**
     * 创建布隆过滤器
     *
     * @PostConstruct：程序启动时候加载此方法
     */
    @PostConstruct
    public void initBloomFilter(){

        //获取所有的USER_INFO键
        Set<String> allKeys = redisService.getUserKeys();
        //创建布隆过滤器实例
        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100000);

        //将redis缓存中的值遍历插入
        for (String key : allKeys){
            String name = (String) redisService.get(key);
            bf.put(name);
        }
    }

    @Override
    //如果布隆过滤器判断存在值则返回true
    public boolean contain(String name) {
        if (bf.mightContain(name)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void add(String name) {
        bf.put(name);
    }


}

