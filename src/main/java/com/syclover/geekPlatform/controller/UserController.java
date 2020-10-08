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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
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
        if (!StringUtils.isEmpty(header_img)){
            user.setHeaderImg(header_img);
        }
        if (!StringUtils.isEmpty(motto)){
            user.setMotto(motto);
        }
        // 如果传入学号和姓名，进入本校学生验证
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(number)){
            if (user.getStudentId() != null){
                return new ResultT(ResponseCode.USER_IS_STUDENT.getCode(),ResponseCode.USER_IS_STUDENT.getMsg(),null);
            }
            // 缓存中查找学生学号是否被注册
            if (redisService.get(RedisUtil.generateStudentKey(number)) == null ){
                // 如果在数据库中找到对应的记录
                if (userService.getStudent(name,number) != null){
                    user.setIsCuit(1);
                    user.setRealName(name);
                    user.setStudentId(number);
                    redisService.set(RedisUtil.generateStudentKey(number),1);
                }else{
                    return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),ResponseCode.PARAMETER_ERROR.getMsg(),null);
                }
            }else{
                return new ResultT(ResponseCode.STUDENT_NUMBER_USED_ERROR.getCode(),ResponseCode.STUDENT_NUMBER_USED_ERROR.getMsg(),null);
            }
        }

        if ( userService.updateProfile(user) == 0){
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
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
    public ResultT getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),allUser);
    }

}
