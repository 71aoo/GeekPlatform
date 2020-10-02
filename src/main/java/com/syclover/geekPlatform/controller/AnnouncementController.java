package com.syclover.geekPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.AnnouncementMapper;
import com.syclover.geekPlatform.service.AnnouncementService;
import com.syclover.geekPlatform.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Set;

/**
 * @Author cueyu
 * @Date 2020/9/15
 */

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AnnouncementMapper announcementMapper;

    @RequestMapping("/getContent")
    public ResultT getContent() {
        try {
            Set<String> announcements = announcementService.getAnnouncements();
            ResultT result = new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), announcements);
            return result;
        }catch (Exception e){
            ResultT result = new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
            return result;
        }
    }

    @RequestMapping("/addContent")
    public ResultT addContent(@Param("content") String content) {
        // 添加成功
        if (content != null && StringUtils.equals(content,"") == false){
            // 查询id 并在缓存中加入
            int result = announcementMapper.addAnnouncement(content);

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
