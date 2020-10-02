package com.syclover.geekPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.AnnouncementMapper;
import com.syclover.geekPlatform.entity.Announcement;
import com.syclover.geekPlatform.service.AnnouncementService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/9/15
 */

@RestController
@RequestMapping("/announce")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 获得所有通知
     * @return
     */
    @RequestMapping("/get")
    public ResultT getContent() {
        try {
            List<Announcement> announcements = announcementService.getAll();
            ResultT result = new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), announcements);
            return result;
        }catch (Exception e){
            ResultT result = new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
            return result;
        }
    }

    /**
     * 添加通知
     * @param content
     * @return
     */
    @RequestMapping("/add")
    public ResultT addContent(String content) {
        // 添加成功
        if (content != null && StringUtils.equals(content,"") == false){
            // 查询id 并在缓存中加入
            Timestamp time = new Timestamp(System.currentTimeMillis());
            int result = announcementMapper.addAnnouncement(content,time);

            if (result == 0){
                ResultT resultT = new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
                return resultT;
            }

            int id = announcementMapper.getLastId().getId();
            announcementService.setAnnouncement(id,content);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }else {
            ResultT result = new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(), ResponseCode.PARAMETER_MISS_ERROR.getMsg(), null);
            return result;
        }
    }

}
