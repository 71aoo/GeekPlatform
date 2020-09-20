package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.SolveMapper;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Solve;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/18
 */
@SpringBootTest
public class SolveMapperTest {

    @Autowired
    private SolveMapper solveMapper;


    @Test
    public void addSolve(){

        User user = new User();
        user.setId(1);

        Team team = new Team();
        team.setId(1);
        team.setToken("ASDASDAS");

        Challenge challenge = new Challenge();
        challenge.setId(51);

        Solve solve = new Solve();
        solve.setUser(user);
        solve.setTeam(team);
        solve.setFlag("asdsad");
        solve.setChallenge(challenge);


        boolean i = solveMapper.addSolve(solve);
        System.out.println(i);

    }


    @Test
    public void getSolvedChallengesByUser(){

        User user = new User();
        user.setId(1);

        List<Challenge> challenges = solveMapper.getSolvedChallengesByUser(user);
        for (Challenge c : challenges){
            System.out.println(c);
        }


    }

    @Test
    public void getSolvedChallengesByTeam(){
        Team team = new Team();
        team.setId(1);

        List<Challenge> challenges = solveMapper.getSolvedChallengesByTeam(team);
        for (Challenge c : challenges){
            System.out.println(c);
        }
    }

    @Test
    public void getPointsByUser(){

        User user = new User();
        user.setId(1);

        int points = solveMapper.getPointsByUser(user);
        System.out.println(points);
    }

    @Test
    public void getPointsByTeam(){
        Team team = new Team();
        team.setId(1);

        int points = solveMapper.getPointsByTeam(team);
        System.out.println(points);
    }

    @Test
    public void isSolve(){

        Solve solve = solveMapper.isSolve(1, "ASDASDAS", 51);

        System.out.println(solve);
    }

    @Test
    public void updatedScore(){

        boolean b = solveMapper.updatedScore(1, 1, 58);
        System.out.println(b);
    }
}
