package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Solve;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/18
 */
@Repository
public interface SolveMapper {

    // 新增
    int addSolve(Solve solve);

    // 找某用户所有解答的题目按时间排序
    List<Challenge> getSolvedChallengesByUser(User user);

    // 找出某队伍所有解答的题目按时间排序
    List<Challenge> getSolvedChallengesByTeam(Team team);

    // 根据 Solve 表得出用户个人总分
    int getPointsByUser(User user);

    // 根据 Solve 表得出队伍总分
    int getPointsByTeam(Team team);

}
