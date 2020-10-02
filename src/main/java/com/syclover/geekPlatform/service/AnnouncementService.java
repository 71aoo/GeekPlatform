package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.entity.Announcement;

import java.util.List;
import java.util.Set;

public interface AnnouncementService {

    public void setAnnouncement(int id,String content);

    public void initAnnouncements();

    public Set<String> getAnnouncements();

    public List<Announcement> getAll();
}
