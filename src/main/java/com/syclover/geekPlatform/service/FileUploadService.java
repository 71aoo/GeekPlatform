package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Playwi0
 * @Data: 2020/10/16
 */
public interface FileUploadService {

    ResultT<String> userUpload(MultipartFile file, User user);

    ResultT<String> teamUpload(MultipartFile file, Team team);

}
