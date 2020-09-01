package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.service.BloomFilterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/8/29
 */

@SpringBootTest
public class BloomServiceTest {

    @Autowired
    private BloomFilterService bloomFilterService;

    @Test
    void test(){
        bloomFilterService.add("admin");
        bloomFilterService.add("testString");
        bloomFilterService.add("teststring");
        System.out.println(bloomFilterService.contain("teststring"));
        System.out.println(bloomFilterService.contain("test"));
        System.out.println(bloomFilterService.contain("testString"));
    }

}
