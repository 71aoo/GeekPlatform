package com.syclover.geekPlatform.mapper;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/15
 */
public interface ChallengeMapper {

    Challenge getById(@Param("ChallengeID") int ChallengeID);

    List<Challenge> getByCategory(int categoryID);

}
