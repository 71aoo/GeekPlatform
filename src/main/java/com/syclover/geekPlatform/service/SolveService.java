package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Solve;

import java.util.List;


/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
public interface SolveService {

    // 解题成功
    ResultT flagSubmit(String flag, String token, int teamID, int userID, int challengeID);


//    // 记录队伍解题记录和队伍分数增加
//    ResultT recordSolve(String flag, String token, int teamID, int userID, int challengeID);


    ResultT<List<Solve>> getTeamSolvedChallenge(int teamId);

    ResultT<List<Solve>> getUserSolvedChallenge(int userId);

}
