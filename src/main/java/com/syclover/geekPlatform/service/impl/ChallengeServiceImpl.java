package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.ChallengeMapper;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeMapper challengeMapper;


    /*
    * 获取某个分类下所有的题目
    * */
    @Override
    public ResultT<List<Challenge>> getChallengesByCategory(Category category) {

        // 参数检查
        if (category == null || category.getId() < 0){

            return new ResultT<List<Challenge>>(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        // 查询
        List<Challenge> challenges = challengeMapper.getByCategoryID(category.getId());

        ResultT<List<Challenge>> resultT = new ResultT<List<Challenge>>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                challenges);

        return resultT;
    }

    /*
    * 查询题目信息
    * */
    @Override
    public ResultT<Challenge> getChallengesByID(int id) {

        // 参数检查
        if (id < 0){

            return new ResultT<Challenge>(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        // 查询
        Challenge challenge = challengeMapper.getByID(id);

        ResultT<Challenge> resultT = new ResultT<Challenge>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                challenge);

        return resultT;
    }
}
