package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.BCPEUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/8/23
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void updateTeamTest(){
        userService.updateTeam(5,4);
    }

    @Test
    public void getTeamId(){
        User user = userService.getUser(6).getData();
        int teamId = userService.getTeamId(user);
        System.out.println("teamId:"+teamId);
    }

    @Test
    public void utilTest(){
        String encodePwd = BCPEUtils.encode("test");
        System.out.println(encodePwd);
        System.out.println(BCPEUtils.matches("tests",encodePwd));
    }
}
