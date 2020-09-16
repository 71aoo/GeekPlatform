package com.syclover.geekPlatform.service;

import java.util.Set;

public interface AnnouncementService {

    public void setAnnouncement(int id,String content);

    public void initAnnouncements();

    public Set<String> getAnnouncements();
}
