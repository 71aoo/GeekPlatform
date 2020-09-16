package com.syclover.geekPlatform.serviceTest;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/9/16
 */

@SpringBootTest
public class AnnouncementServiceTest {

    @Autowired
    private AnnouncementService announcementService;

    @Test
    public void Test(){
        Set<String> announcements = announcementService.getAnnouncements();
        ResultT result = new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),announcements);
        System.out.println(JSON.toJSONString(result));
    }

}
