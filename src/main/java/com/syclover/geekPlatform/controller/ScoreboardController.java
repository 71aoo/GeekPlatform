package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@RestController
@RequestMapping("/scoreboard")
public class ScoreboardController {


    @Autowired
    private ScoreboardService scoreboardService;


    /**
     * 队伍集积分榜
     * @return
     */
    @RequestMapping("/team")
    @ResponseBody
    public ResultT<List<Team>> teamsScoreboard(){

        ResultT<List<Team>> resultT = scoreboardService.allTeamsScoreboard();

        return resultT;
    }

    /**
     * 个人积分榜
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public ResultT<List<User>> usersScoreboard(){

        ResultT<List<User>> resultT = scoreboardService.allUsersScoreboard();

        return resultT;
    }


}
