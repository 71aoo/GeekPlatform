package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.RedisService;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.service.UserService;
import com.syclover.geekPlatform.util.RedisUtil;
import com.syclover.geekPlatform.util.SessionGetterUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.List;
import java.util.UUID;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */

@RestController
@RequestMapping("/team/")
public class TeamController {


    @Autowired
    @Qualifier("bloomFilterTeamImpl")
    private BloomFilterService bloomFilterService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

//    @PostMapping("/add")
//    public ResultT createTeam(@Param("teamname") String teamname, HttpSession session) throws Exception{
//        List<String> names = teamService.getAllName().getData();
//        if (names.contains(teamname)){
//            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
//        }
//        Team team = new Team();
//        User user = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
//        if (userService.getTeamId(user) == 0) {
//            String token = UUID.randomUUID().toString().replace("-", "");
//            team.setName(teamname);
//            team.setMemberOne(user);
//            team.setToken(token);
//            ResultT<Team> team1 = teamService.createTeam(team);
//            int getTeamId = teamService.getTeam(teamname).getData().getId();
//            userService.updateTeam(user.getId(),getTeamId);
//            return team1;
//        }else{
//            //用户已在一只队伍中
//            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
//        }
//    }

    @PostMapping("/create")
    public ResultT createTeam(String teamName,String img,String motto,HttpSession session){
        User user = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
        user.setPassword(null);
        if (user.getTeamId() != 0){
            return new ResultT(ResponseCode.USER_HAS_IN_TEAM.getCode(),ResponseCode.USER_HAS_IN_TEAM.getMsg(),null);
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        Team team = new Team();
        if (!StringUtils.isEmpty(teamName) || !StringUtils.isEmpty(img) || !StringUtils.isEmpty(motto)){
            if (bloomFilterService.contain(teamName)){
                return new ResultT(ResponseCode.TEAM_NAME_USED.getCode(),ResponseCode.TEAM_NAME_USED.getMsg(),null);
            }
            List<String> names = teamService.getAllName().getData();
            if (names.contains(teamName)){
                return new ResultT(ResponseCode.TEAM_NAME_USED.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
            }
            team.setMemberOne(user);
            team.setToken(token);
            team.setName(teamName);
            team.setHeaderImg(img);
            team.setMotto(motto);
            ResultT<Team> data = teamService.createTeam(team);
            userService.updateTeam(data.getData().getId(),user.getId());
            System.out.println("id:" + data.getData().getId());
            redisService.set(RedisUtil.generateTeamKey(data.getData().getId()),teamName);
            bloomFilterService.add(teamName);
            return data;
        }else{
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

    }


    @PostMapping("/join")
    public ResultT joinTeam(@Param("token") String token,HttpSession session) throws Exception{
        User user = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
        if (user.getTeamId() != 0){
            return new ResultT(ResponseCode.USER_HAS_IN_TEAM.getCode(),ResponseCode.USER_HAS_IN_TEAM.getMsg(),null);
        }
        Team team = teamService.getTeamByToken(token).getData();
        if (team != null){
            if (team.getMemberTwo() == null){
                teamService.addTeamate(user.getId(),token);
                userService.updateTeam(user.getId(),team.getId());
                return new ResultT(ResponseCode.TEAM_JOIN_SUCCESS.getCode(),ResponseCode.TEAM_JOIN_SUCCESS.getMsg(),null);
            }else {
                return new ResultT(ResponseCode.TEAM_JOIN_FAILED.getCode(),ResponseCode.TEAM_JOIN_FAILED.getMsg(),null);
            }
        }
        else{
            return new ResultT(ResponseCode.TEAM_NOT_FOUND.getCode(),ResponseCode.TEAM_NOT_FOUND.getMsg(),null);
        }
    }

    @PostMapping("/check")
    public ResultT checkNameVariable(@Param("name") String name){
        if (bloomFilterService.contain(name)){
            //名称已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }

}
