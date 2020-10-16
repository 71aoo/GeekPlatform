package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.TeamMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.FileUploadService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.AliyunOSSUtils;
import com.syclover.geekPlatform.util.CleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Playwi0
 * @Data: 2020/10/16
 */
@Service
public class FileUploadServiceImp implements FileUploadService {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public ResultT<String> userUpload(MultipartFile file, User user) {

        // 获取后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            ResultT<String> resultT = AliyunOSSUtils.uploadFile(file.getInputStream(), suffix);

            if (resultT.getStatus() == 200) {

                user.setHeaderImg(resultT.getData().trim());


                if (userService.updateProfile(user) == 0) {

                    return new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);

                } else {

                    user = CleanUtil.getSelfInfo(user);
                    return new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), user);
                }
            }

            return resultT;

        } catch (Exception e) {

            return new ResultT<String>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }

    }


    @Override
    public ResultT<String> teamUpload(MultipartFile file, Team team) {

        // 获取后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            ResultT<String> resultT = AliyunOSSUtils.uploadFile(file.getInputStream(), suffix);

            if (resultT.getStatus() == 200){

                team.setHeaderImg(resultT.getData().trim());

                if (teamMapper.updateTeamInfo(team) == 0){
                    return new ResultT(ResponseCode.TEAM_UPDATE_ERROR.getCode(),ResponseCode.TEAM_UPDATE_ERROR.getMsg(),null);

                }else{
                    Team cleanTeam = CleanUtil.cleanTeamWithToken(team);
                    return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cleanTeam);
                }
            }

            return resultT;

        }catch (Exception e){
            return new ResultT<String>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }
    }
}
