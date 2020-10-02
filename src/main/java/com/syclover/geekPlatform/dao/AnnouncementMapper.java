package com.syclover.geekPlatform.dao;


import com.syclover.geekPlatform.entity.Announcement;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AnnouncementMapper {

    List<Announcement> getAnnouncements();

    // 管理员接口 用于添加通知
    int addAnnouncement(String content, Timestamp createdTime);

    Announcement getLastId();
}
