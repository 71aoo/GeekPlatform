package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Solve;
import com.syclover.geekPlatform.service.impl.SolveServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/10/18
 */
@SpringBootTest
public class SolveServiceImplTest {

    @Autowired
    private SolveServiceImpl solveService;

    @Test
    public void getUser(){

        ResultT<List<Solve>> userSolvedChallenge = solveService.getUserSolvedChallenge(49);


    }
}
