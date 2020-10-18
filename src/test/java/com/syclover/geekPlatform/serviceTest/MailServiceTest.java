package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.MailService;
import com.syclover.geekPlatform.service.ScoreboardService;
import com.syclover.geekPlatform.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void testSimpleMail(){
        for (int i=0;i<50;i++) {
            String content = "test Email content:http://www.cueyu.com";
            mailService.sendSimpleMail("cueyu@foxmail.com", "test simple mail", content);
        }
    }

    @Test
    public void getUserService() {
        List<User> allUser = userService.getAllUser(1);
        System.out.println(allUser);
    }


    @Test
    void getAll(){
        ResultT<List<Team>> listResultT = scoreboardService.allTeamsScoreboard(1);
        ResultT<List<User>> listResultT1 = scoreboardService.allUsersScoreboard(1);
        System.out.println("TEam:"+listResultT);
        System.out.println("User:"+ listResultT1);
    }
}
