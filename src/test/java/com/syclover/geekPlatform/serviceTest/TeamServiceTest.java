package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/8/23
 */

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void getAllName() {
        List<String> data = teamService.getAllName().getData();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

    }
}
