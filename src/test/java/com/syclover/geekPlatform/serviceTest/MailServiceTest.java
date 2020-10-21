package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.SolveMapper;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.entity.Challenge;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.*;
import com.syclover.geekPlatform.util.CleanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/9/4
 */
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreboardService scoreboardService;

    @Autowired
    private SolveMapper solveMapper;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private CategoryService categoryService;


    @Test
    public void testSimpleMail(){
        for (int i=0;i<50;i++) {
            String content = "test Email content:http://www.cueyu.com";
            mailService.sendSimpleMail("cueyu@foxmail.com", "test simple mail", content);
        }
    }

    @Test
    public void getUserService() {
        Category data = categoryService.getCategoryByID(6).getData();
        List<Challenge> data1 = challengeService.getChallengesByCategory(data).getData();
        System.out.println(data1);
    }


    @Test
    void getAll(){
        ResultT<List<Team>> listResultT = scoreboardService.allTeamsScoreboard(1);
        ResultT<List<User>> listResultT1 = scoreboardService.allUsersScoreboard(1);
        System.out.println("TEam:"+listResultT);
        System.out.println("User:"+ listResultT1);
    }

    @Test
    void solveTest(){
        List<User> userSolveInfo = solveMapper.getUserSolveInfo(103, 0);
        List<User> cleanInfoList = new ArrayList();
        for (User user : userSolveInfo){
            User cleanUser = CleanUtil.cleanUser(user);
            cleanInfoList.add(cleanUser);
        }

        System.out.println(cleanInfoList);
    }
}
