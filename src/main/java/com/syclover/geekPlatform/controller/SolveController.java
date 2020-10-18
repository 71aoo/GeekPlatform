package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.SolveMapper;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Solve;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.SolveService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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

        if (user.getId() != userID || teamID != team.getId() || !token.equals(team.getToken()) ){

            System.out.println(token);
            System.out.println(team.getToken());

            return new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }

        ResultT resultT = solveService.flagSubmit(flag, team.getToken(), team.getId(), user.getId(), challengeID);

        return resultT;
    }


    @GetMapping("/team/challenge")
    @ResponseBody
    public ResultT<List<Solve>> getTeamSolvedChallenges(Integer teamID){
        if (teamID == null){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        return  solveService.getTeamSolvedChallenge(teamID);
    }

    @GetMapping("/user/challenge")
    @ResponseBody
    public ResultT<List<Solve>> getUserSolvedChallenges(Integer userID){
        if (userID == null){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        return solveService.getUserSolvedChallenge(userID);
    }

}
