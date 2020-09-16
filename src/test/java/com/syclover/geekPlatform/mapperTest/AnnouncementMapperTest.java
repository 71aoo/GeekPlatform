package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.AnnouncementMapper;
import com.syclover.geekPlatform.entity.Announcement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/9/15
 */

@SpringBootTest
public class AnnouncementMapperTest {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Test
    public void Test(){
        List<Announcement> announcements = announcementMapper.getAnnouncements();
        for (Announcement a : announcements){
            System.out.println(a);
            String id = String.valueOf(a.getId());
            System.out.println("id:" + id);
        }
    }

    @Test
    public void addTest(){
        int result = announcementMapper.addAnnouncement("we kick for the goal");
        System.out.println(result);
    }

}
