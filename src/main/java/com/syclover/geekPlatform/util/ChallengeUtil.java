package com.syclover.geekPlatform.util;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/10/7
 */
public class ChallengeUtil {

    public static List<Challenge> signSolvedChallenges(List<Challenge> challenge, List<Challenge> solvedChallenge){

        // 没有解出题目直接返回
        if (solvedChallenge == null || solvedChallenge.isEmpty()) {
            return challenge;
        }

        // 取出已解决题目id
        ArrayList solveChallengesId = new ArrayList();
        for (Challenge sc : solvedChallenge){

            solveChallengesId.add(sc.getId());
        }

        // 标记已解决题目
        for (Challenge c : challenge){
            if (solveChallengesId.contains(c.getId())){
                c.setIsSolved(1);
            }
        }

        return challenge;
    }
}
