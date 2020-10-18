package com.syclover.geekPlatform.service.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/9/1
 */


//存放有关team信息的bloom过滤器

@Service
public class BloomFilterTeamImpl implements BloomFilterService {

    @Autowired
    private RedisService redisService;

    private BloomFilter<String> bf;

    @Override
    @PostConstruct
    //初始化并获得缓存中team的数据
    public void initBloomFilter() {
        //获取所有的TEAM_INFO键
        Set<String> allKeys = redisService.getTeamKeys();
        //创建布隆过滤器实例
        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100000);

        //将redis缓存中的值遍历插入
        for (String key : allKeys){
            String name = (String) redisService.get(key);
            bf.put(name);
        }
    }

    @Override
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
