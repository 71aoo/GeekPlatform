package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.ScoreboardMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/28
 */
@SpringBootTest
public class ScoreboardMapperTest {

    @Autowired
    private ScoreboardMapper scoreboardMapper;

    @Test
    public void getTeamScoreboard(){

        List<Team> teamScoreboard = scoreboardMapper.getTeamScoreboard();
        for (Team t: teamScoreboard){
            System.out.println(t);
        }
    }

    @Test
    public void getUserScoreboard(){
        List<User> userScoreboard = scoreboardMapper.getUserScoreboard();
        for (User u: userScoreboard){
            System.out.println(u);
        }
    }

}
