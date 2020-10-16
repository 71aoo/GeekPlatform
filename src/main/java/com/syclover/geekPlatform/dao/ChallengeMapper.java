package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Challenge;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/15
 */
@Repository
public interface ChallengeMapper {

    // 根据id查询
    Challenge getByID(int id);

    // 根据Category id 查询
    List<Challenge> getByCategoryID(int id);

    // 根据Category name 查询
    List<Challenge> getByCategoryName(String name);

    // 根据 Challenge id 修改字段
    boolean updateByID(Challenge challenge);

    // 增加
    boolean addChallenge(Challenge challenge);

    // 根据 id 逻辑删除一个 challenge
    boolean logicallyDelByID(int id);

    // 标记题目已有解
    boolean updatedFirstBlood(int challengeID);

    String selectFlagById(Integer id);
}
