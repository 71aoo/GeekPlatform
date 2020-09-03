package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.BloomFilterService;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */

@Controller
@RequestMapping("/api")
public class TeamController {


    @Autowired
    @Qualifier("bloomFilterTeamImpl")
    private BloomFilterService bloomFilterService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @PostMapping("/team/add")
    @ResponseBody
    public ResultT createTeam(@Param("teamname") String teamname, HttpSession session) {
        List<String> names = teamService.getAllName().getData();
        if (names.contains(teamname)){
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }
        Team team = new Team();
        User user = userService.getLoginUser((String) session.getAttribute("user")).getData();
        if (userService.getTeamId(user) == 0) {
            String token = UUID.randomUUID().toString().replace("-", "");
            team.setName(teamname);
            team.setMemberOne(user);
            team.setToken(token);
            ResultT<Team> team1 = teamService.createTeam(team);
            int getTeamId = teamService.getTeam(teamname).getData().getId();
            userService.updateTeam(user.getId(),getTeamId);
            return team1;
        }else{
            //用户已在一只队伍中
            return new ResultT(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }
    }

    @PostMapping("/team/join")
    @ResponseBody
    public ResultT joinTeam(@Param("token") String token,HttpSession session){
        User user = userService.getLoginUser((String) session.getAttribute("user")).getData();
        int teamId = user.getTeamId();
        if (teamId == 0){
            teamService.addTeamate(user.getId(),token);
            int id = teamService.getTeamByToken(token).getData().getId();
            userService.updateTeam(user.getId(),id);
            return new ResultT<>(ResponseCode.TEAM_JOIN_SUCCEESS.getCode(),ResponseCode.TEAM_JOIN_SUCCEESS.getMsg(),null);
        }else{
            return new ResultT<>(ResponseCode.TEAM_JOIN_FAILED.getCode(),ResponseCode.TEAM_JOIN_FAILED.getMsg(),null);
        }
    }

    @PostMapping("/team/check")
    @ResponseBody
    public ResultT checkNameVariable(@Param("name") String name){
        List<String> names = teamService.getAllName().getData();
        if (names.contains(name)){
            //名称已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
        }
    }

}
