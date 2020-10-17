package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.FileUploadService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Author: Playwi0
 * @Data: 2020/10/16
 */
@RequestMapping("/upload")
@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResultT<String> userUpload(HttpSession session, MultipartFile file){

        if (file == null){
            return new ResultT<String>(ResponseCode.ERROR.getCode(), "文件为空", null);
        }

        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        ResultT<String> resultT = fileUploadService.userUpload(file, user);

        return resultT;

    }


    @PostMapping("/team")
    @ResponseBody
    public ResultT<String> teamUpload(HttpSession session, MultipartFile file){

        if (file == null){
            return new ResultT<String>(ResponseCode.ERROR.getCode(), "文件为空", null);
        }

        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        if (user.getTeamId() == 0 || user.getTeam() == null){
            return new ResultT(ResponseCode.TEAM_NOT_FOUND.getCode(), ResponseCode.TEAM_NOT_FOUND.getMsg(), null);
        }

        Team team = user.getTeam();

        ResultT<String> resultT = fileUploadService.teamUpload(file, team);

        return resultT;

    }
}
