package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.CleanUtil;
import com.syclover.geekPlatform.util.RedisUtil;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/9/20
 */


@RestController
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 用户更新个人资料
     * @param session
     * @param motto
     * @param header_img
     * @param name
     * @param number
     * @return
     */
    @PostMapping("/update")
    public ResultT getProfile(HttpSession session,String motto,String header_img,String name,String number){
        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        if (StringUtils.isEmpty(header_img)){
            user.setHeaderImg("https://geekplateform.oss-cn-beijing.aliyuncs.com/BaseHeaderImg.png?x-oss-process=image/auto-orient,1/quality,q_67");
        } else if (header_img.equals("https://geekplateform.oss-cn-beijing.aliyuncs.com/BaseHeaderImg.png?x-oss-process=image/auto-orient,1/quality,q_67")) {
            user.setHeaderImg(header_img);
        } else {
            if (header_img.length() >= 200){
                return new ResultT(ResponseCode.INPUT_LENGTH_TOO_LONG.getCode(),ResponseCode.INPUT_LENGTH_TOO_LONG.getMsg(),null);
            }
            header_img = header_img + "?x-oss-process=image/auto-orient,1/quality,q_67";
            user.setHeaderImg(header_img);
        }


        if (StringUtils.isEmpty(motto)){
            user.setMotto(null);
        }else if (motto.length() > 20){
            return new ResultT(ResponseCode.MOTTO_LENGTH_TOO_LONG.getCode(),ResponseCode.MOTTO_LENGTH_TOO_LONG.getMsg(),null);
        }



        if (StringUtils.isEmpty(name)){
            user.setRealName(null);
        }else if (name.length() > 10){
            return new ResultT(213,"真实姓名过长",null);
        }


        if (StringUtils.isEmpty(number)){
            user.setStudentId(null);
            user.setIsCuit(0);
        }else{
            if (number.length() != 10){
                return new ResultT(ResponseCode.STUDENT_NUMBER_LENGTH_ERROR.getCode(),ResponseCode.STUDENT_NUMBER_LENGTH_ERROR.getMsg(),number);
            }
            user.setIsCuit(1);
        }

        user.setMotto(motto);
        user.setRealName(name);
        user.setStudentId(number);

        if ( userService.updateProfile(user) == 0){
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            user = CleanUtil.getSelfInfo(user);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),user);
        }
    }

    /**
     * 用户登陆后修改密码
     * @param session
     * @param oldPass
     * @param newPass
     * @return
     */
    @PostMapping("/changePassword")
    public ResultT changePasswd(HttpSession session,String oldPass,String newPass){
        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }
        return userService.changePass(user,oldPass,newPass);
    }

    /**
     * 获得用户数据接口
     * @param session
     * @return
     */
    @GetMapping("/getData")
    public ResultT getData(HttpSession session){
        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }
        user = CleanUtil.getSelfInfo(user);
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),user);
    }

    /**
     * 根据id获得用户接口
     * @param id
     * @return
     */
    @GetMapping("/getInfoById")
    public ResultT getUserById(Integer id){
        if (id == null){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null  );
        }
        User user = userService.getTeamUser(id);
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),user);
    }

    /**
     * 获取所有用户信息，按姓名排序
     * @return
     */
    @GetMapping("/getAll")
    public ResultT getAllUser(Integer page){

        if (page == null || page <= 0){
            page = 1;
        }

        page = (page -1) * 50;

        List<User> allUser = userService.getAllUser(page);
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),allUser);
    }

}
