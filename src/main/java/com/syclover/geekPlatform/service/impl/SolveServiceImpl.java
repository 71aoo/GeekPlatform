package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.ChallengeMapper;
import com.syclover.geekPlatform.dao.SolveMapper;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Solve;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.SolveService;
import com.syclover.geekPlatform.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
@Service
public class SolveServiceImpl implements SolveService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private SolveMapper solveMapper;

    @Autowired
    private ChallengeMapper challengeMapper;




    private final static Object LOCK_OBJECT = new Object();


    @Override
    public ResultT flagSubmit(String flag, String token, int teamID, int userID, int challengeID) {

        // 参数验证
        if (flag == null || teamID < 0 || userID < 0){

            return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getMsg(), null);
        }

        //判断用户是否在这个队伍里面
        ResultT<Team> resultT = teamService.checkUserInTeam(token, teamID, userID);

        if (resultT.getStatus() != 200){

            return resultT;
        }

        // 判断题目是否已解
        Solve solve = solveMapper.isSolve(teamID, token, challengeID);

        if (solve != null){
            return new ResultT(ResponseCode.CHALLENGE_HAS_BE_SOLVED.getCode(), ResponseCode.CHALLENGE_HAS_BE_SOLVED.getMsg(), null);
        }

        // 添加题目
        synchronized (LOCK_OBJECT){

            // 判断题目是否已解
            solve = solveMapper.isSolve(teamID, token, challengeID);

            if (solve != null){
                return new ResultT(ResponseCode.CHALLENGE_HAS_BE_SOLVED.getCode(), ResponseCode.CHALLENGE_HAS_BE_SOLVED.getMsg(), null);
            }

            ResultT recordSolveResultT = recordSolve(flag, token, teamID, userID, challengeID);

            return recordSolveResultT;
        }

    }

    /*
    * synchronized 同步执行
    * 更新用户，队伍分数，并添加一条 solve 记录
    * */
    @Transactional(rollbackFor = Exception.class)
    protected ResultT recordSolve(String flag, String token, int teamID, int userID, int challengeID) {

        // 包装成solve类
        User user = new User(userID);
        Team team = new Team(teamID);
        Challenge challenge = new Challenge(challengeID);
        Solve solve = new Solve();

        solve.setUser(user);
        solve.setTeam(team);
        solve.setChallenge(challenge);
        solve.setToken(token);
        solve.setFlag(flag);

        // 数据库插入
        boolean addSolve = solveMapper.addSolve(solve);
        boolean updatedScore = solveMapper.updatedScore(teamID, userID, challengeID);
        boolean b = challengeMapper.updatedFirstBlood(challengeID);

        if (!addSolve || !updatedScore || !b){
            return new ResultT(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }

        return new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), null);
    }
}
