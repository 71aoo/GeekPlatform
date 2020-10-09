package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.SolveService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@RestController
@RequestMapping("/solve")
public class SolveController {

    @Autowired
    private SolveService solveService;

    @Autowired
    private UserService userService;


    @RequestMapping("/checkFlag")
    @ResponseBody
    public ResultT submitFlag(String flag, String token,
                              int teamID, int userID, int challengeID, HttpSession session){

        String username = SessionGetterUtil.getUsername(session);
        User user = userService.getLoginUser(username).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        Team team = user.getTeam();
        if (team == null){
            return new ResultT(ResponseCode.TEAM_NOT_FOUND.getCode(), ResponseCode.TEAM_NOT_FOUND.getMsg(), null);
        }

        if (user.getId() != userID || teamID != team.getId() || token != team.getToken()){

            return new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }

        ResultT resultT = solveService.flagSubmit(flag, team.getToken(), team.getId(), user.getId(), challengeID);

        return resultT;
    }
}
