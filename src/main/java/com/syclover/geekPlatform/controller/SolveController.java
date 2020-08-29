package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.service.SolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@RestController
@RequestMapping("/solve")
public class SolveController {

    @Autowired
    private SolveService solveService;


    @RequestMapping("/checkFlag")
    @ResponseBody
    public ResultT submitFlag(String flag, String token,
                              int teamID, int userID, int challengeID){

        ResultT resultT = solveService.flagSubmit(flag, token, teamID, userID, challengeID);

        return resultT;
    }
}
