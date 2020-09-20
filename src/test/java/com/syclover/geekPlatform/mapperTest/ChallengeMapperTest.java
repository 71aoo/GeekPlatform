package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.ChallengeMapper;
import com.syclover.geekPlatform.entity.Challenge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/17
 */
@SpringBootTest
public class ChallengeMapperTest {

    @Autowired
    private ChallengeMapper challengeMapper;

    @Test
    public void getByID(){

        Challenge byID = challengeMapper.getByID(52);

        System.out.println(byID);
    }

    @Test
    public void getByCategoryID(){
        List<Challenge> byCategoryID = challengeMapper.getByCategoryID(5);
        for (Challenge challenge: byCategoryID){
            System.out.println(challenge);
        }
    }

    @Test
    public void getByCategoryName(){

        List<Challenge> pwn = challengeMapper.getByCategoryName("pwn");

        for (Challenge challenge: pwn){

            System.out.println(challenge);
        }

    }

    @Test
    public void updateByID(){

        Challenge byID = challengeMapper.getByID(52);
        byID.setScore(100);
        boolean i = challengeMapper.updateByID(byID);
        System.out.println(i);

    }

    @Test
    public void addChallenge(){
//        Challenge byID = challengeMapper.getByID(52);
//        byID.setName("new");
//        byID.setScore(200);

        Challenge challenge = new Challenge();
        challenge.setName("ttttt");
        challenge.setScore(200);
        challenge.setHidden(0);

        boolean b = challengeMapper.addChallenge(challenge);
        System.out.println(b);
    }

    @Test
    public void logicallyDelByID(){

        boolean b = challengeMapper.logicallyDelByID(102);
        System.out.println(b);
    }

    @Test
    public void updatedFirstBlood(){

        boolean b = challengeMapper.updatedFirstBlood(2);
        System.out.println(b);
    }
}
