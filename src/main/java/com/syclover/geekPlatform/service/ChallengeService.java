package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
public interface ChallengeService {

    // 获取分类下所有的题目
    ResultT<List<Challenge>> getChallengesByCategory(Category category);

    // 查询某个题目
    ResultT<Challenge> getChallengesByID(int id);

    // 修改题目信息
    ResultT changeChallengeInfo(Challenge challenge);

    // 添加题目
    ResultT addChallenge(Challenge challenge);

    // 根据 id 逻辑删除一个题目
    ResultT LogicallyDelChallenge(int id);

    // 标记已有解
    ResultT signSolved(int challengeID);
}
