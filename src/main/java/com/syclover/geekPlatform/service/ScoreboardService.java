package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
public interface ScoreboardService {

    // 队伍积分榜
    ResultT<List<Team>> allTeamsScoreboard(int page);

    // 用户积分榜
    ResultT<List<User>> allUsersScoreboard(int page);

}
