package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.service.SolveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Playwi0
 * @Data: 2020/8/28
 */
@SpringBootTest
public class SolveServiceTest {

    @Autowired
    private SolveService solveService;

    @Test
    public void flagSubmit(){

        ResultT resultT = solveService.flagSubmit("ASDASDAS", "ASDASDAS", 1, 1, 60);

        System.out.println(resultT);
    }


}
