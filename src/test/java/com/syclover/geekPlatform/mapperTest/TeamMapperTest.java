package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.TeamMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */

@SpringBootTest
public class TeamMapperTest {

    @Autowired
    TeamMapper teamMapper;

    @Test
    public void getAll(){
        List<Team> allName = teamMapper.getAllName();
        for (Team team : allName){
            System.out.println(team.getName());
        }
    }

    @Test
    public void isUserInTeam(){

        Team team = teamMapper.isUserInTeam("ASDASDAS", 1, 2);

        System.out.println(team);

    }

    @Test
    void getTeam(){
        int id = 14;
        Team userTeam = teamMapper.getUserTeamById(14);
        System.out.println(userTeam);
    }
}
