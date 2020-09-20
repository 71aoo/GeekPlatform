package com.syclover.geekPlatform.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Author;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@RestController
@RequestMapping("challenge")
public class ChallengeController {


    @Autowired
    private ChallengeService challengeService;


    /**
     * 新增一个题目
     * @param name
     * @param intro
     * @param hint
     * @param score
     * @param flag
     * @param authorID
     * @param categoryID
     * @param hidden
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultT addChallenge(String name, String intro, String hint,
                                int score, String flag, int authorID,
                                int categoryID, int hidden){

        Challenge challenge = new Challenge();
        challenge.setName(name);
        challenge.setIntro(intro);
        challenge.setHint(hint);
        challenge.setScore(score);
        challenge.setFlag(flag);
        challenge.setAuthor(new Author(authorID));


        challenge.setCategory(new Category(categoryID));
        challenge.setHidden(hidden);

        ResultT resultT = challengeService.addChallenge(challenge);

        return resultT;
    }

    /**
     * 删除一个题目
     * @param challengeID
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public ResultT DelChallenge(int challengeID){

        ResultT resultT = challengeService.LogicallyDelChallenge(challengeID);

        return resultT;

    }

    /**
     * 修改 challenge 信息
     * @param id
     * @param name
     * @param intro
     * @param hint
     * @param score
     * @param flag
     * @param authorID
     * @param categoryID
     * @param firstBlood
     * @param hidden
     * @return
     */
    @RequestMapping("/challengeInfo")
    @ResponseBody
    public ResultT changeInfo(int id, String name, String intro,
                              String hint, int score, String flag,
                              int authorID, int categoryID, int firstBlood,
                              int hidden){

        Challenge challenge = new Challenge(id);
        challenge.setName(name);
        challenge.setIntro(intro);
        challenge.setHint(hint);
        challenge.setScore(score);
        challenge.setFlag(flag);
        challenge.setAuthor(new Author(authorID));
        challenge.setCategory(new Category(categoryID));
        challenge.setHidden(hidden);
        challenge.setFirstBlood(firstBlood);

        ResultT resultT = challengeService.changeChallengeInfo(challenge);

        return resultT;
    }

    /**
     * 根据分类获取题目
     * @param categoryID
     * @return
     */
    @RequestMapping("/category")
    @ResponseBody
    public ResultT<List<Challenge>> getChallengesByCategory(int categoryID){

        Category category = new Category(categoryID);

        ResultT<List<Challenge>> challenges = challengeService.getChallengesByCategory(category);

        return challenges;
    }

    /**
     * 根据 ID 获取信息
     * @param challengeID
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public ResultT<Challenge> getChallengeByID(int challengeID){

        ResultT<Challenge> challenge = challengeService.getChallengesByID(challengeID);

        return challenge;
    }







}
