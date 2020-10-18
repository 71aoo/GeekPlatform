package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.ScoreboardMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.ScoreboardService;
import com.syclover.geekPlatform.util.CleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@Service
public class ScoreboardServiceImpl implements ScoreboardService {

    @Autowired
    private ScoreboardMapper scoreboardMapper;


    @Override
    public ResultT<List<Team>> allTeamsScoreboard(int page) {

        List<Team> teams = scoreboardMapper.getTeamScoreboard(page);
        List<Team> cleanTeams = new ArrayList<>();
        for (Team team : teams){
            Team cleanTeam = CleanUtil.cleanTeam(team);
            cleanTeams.add(cleanTeam);
        }
        return new ResultT<List<Team>>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), cleanTeams);
    }

    @Override
    public ResultT<List<User>> allUsersScoreboard(int page) {

        List<User> users = scoreboardMapper.getUserScoreboard(page);
        List<User> cleanUsers = new ArrayList<>();
        for (User user : users){
            CleanUtil.cleanUser(user);
            cleanUsers.add(user);
        }
        return new ResultT<List<User>>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), cleanUsers);
    }
}
