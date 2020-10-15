package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.ChallengeMapper;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.transport.Channel;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
@Service
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
        for (Challenge challenge:challenges){
            challenge.setFlag("想看没门");
        }
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
        challenge.setFlag("想看没门");
        ResultT<Challenge> resultT = new ResultT<Challenge>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                challenge);

        return resultT;
    }

    /*
    * 修改题目信息
    * */
    @Override
    public ResultT changeChallengeInfo(Challenge challenge) {

        // 参数检查
        if (challenge == null || challenge.getId() < 0){

            return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        ResultT resultT = null;
        // 修改
        boolean b = challengeMapper.updateByID(challenge);
        if (b){

            // 修改成功
            resultT = new ResultT<Challenge>(ResponseCode.SUCCESS.getCode(),
                    ResponseCode.SUCCESS.getMsg(),
                    challenge);

        }else {

            // 修改失败
            resultT = new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        return resultT;
    }

    /*
    * 添加题目
    * */
    @Override
    public ResultT addChallenge(Challenge challenge) {

        // 参数检查
        if (challenge == null || challenge.getName() == null || challenge.getScore() < 0 || challenge.getScore() < 0){

            return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        ResultT resultT = null;
        boolean b = challengeMapper.addChallenge(challenge);
        if (b){
            // 添加成功
            resultT = new ResultT(ResponseCode.SUCCESS.getCode(),
                    ResponseCode.SUCCESS.getMsg(),
                    null);
        }else {
            // 添加失败
            resultT = new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        return resultT;
    }

    /*
    * 逻辑删除
    * */
    @Override
    public ResultT LogicallyDelChallenge(int id) {

        // 参数检查
        if (id < 0){

            return new ResultT<Challenge>(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        ResultT resultT = null;
        boolean b = challengeMapper.logicallyDelByID(id);
        if (b){

            // 删除成功
            resultT = new ResultT(ResponseCode.SUCCESS.getCode(),
                    ResponseCode.SUCCESS.getMsg(),
                    null);
        }else {

            // 删除失败
            resultT = new ResultT(ResponseCode.FAIL.getCode(),
                    ResponseCode.FAIL.getMsg(),
                    null);
        }

        return resultT;
    }

    @Override
    public ResultT signSolved(int challengeID) {

        if (challengeID < 0){
            return new ResultT(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(),
                    null);
        }

        boolean b = challengeMapper.updatedFirstBlood(challengeID);

        return new ResultT(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), null);
    }


}
