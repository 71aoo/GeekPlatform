package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.entity.AliyunOSSParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/10/13
 */
@SpringBootTest
public class TimeTest {

    @Autowired
    public AliyunOSSParam aliyunOSSParam;

    @Test
    public void timeTest(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void aliTest(){

        System.out.println(aliyunOSSParam.getBucketName());
        System.out.println(aliyunOSSParam.getEndpoint());
        System.out.println(aliyunOSSParam.getAccessKeyId());
        System.out.println(aliyunOSSParam.getAccessKeySecret());
    }

}
