package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.dao.AnnouncementMapper;
import com.syclover.geekPlatform.entity.Announcement;
import com.syclover.geekPlatform.service.AnnouncementService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/9/16
 */

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private RedisServiceImpl redisService;

    // 初始化 从数据库中读通知 写入redis
    @PostConstruct
    public void initAnnouncements(){
        List<Announcement> announcements = announcementMapper.getAnnouncements();
        for (Announcement announcement : announcements){
            redisService.set(String.valueOf(RedisUtil.generateAnnouncementKey(announcement.getId())),announcement.getContent());
        }
    }

    public void setAnnouncement(int id,String content){
        redisService.set(RedisUtil.generateAnnouncementKey(id),content );
    }

    public Set<String> getAnnouncements(){
        Set<String> contents = new HashSet<String>();
        Set<String> announcementsKeys = redisService.getAnnouncementsKeys();
        for (String key : announcementsKeys){
            String content = (String) redisService.get(key);
            contents.add(content);
        }

        return contents;

    }

}
