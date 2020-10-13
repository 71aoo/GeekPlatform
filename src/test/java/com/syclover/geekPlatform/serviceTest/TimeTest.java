package com.syclover.geekPlatform.serviceTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/10/13
 */
@SpringBootTest
public class TimeTest {

    @Test
    public void timeTest(){
        System.out.println(System.currentTimeMillis());
    }

}
