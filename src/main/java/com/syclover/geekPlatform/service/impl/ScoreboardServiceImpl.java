package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.ScoreboardMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResultT<List<Team>> allTeamsScoreboard() {

        List<Team> teams = scoreboardMapper.getTeamScoreboard();

        return new ResultT<List<Team>>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), teams);
    }

    @Override
    public ResultT<List<User>> allUsersScoreboard() {

        List<User> users = scoreboardMapper.getUserScoreboard();

        return new ResultT<List<User>>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), users);
    }
}
