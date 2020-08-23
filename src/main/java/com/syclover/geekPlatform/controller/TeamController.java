package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public String createTeam(@Param("teamname") String teamname, HttpSession session) {
        List<String> names = teamService.getAllName().getData();
        if (names.contains(teamname)){
            return "队伍名已被注册！";
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
            return team1.getMsg();
        }else{
            return "您已经在一只队伍中，请勿重新创建队伍";
        }
    }

    @PostMapping("/join")
    @ResponseBody
    public String joinTeam(@Param("token") String token,HttpSession session){
        User user = userService.getLoginUser((String) session.getAttribute("user")).getData();
        int teamId = user.getTeamId();
        if (teamId == 0){
            teamService.addTeamate(user.getId(),token);
            long id = teamService.getTeamByToken(token).getData().getId();
            userService.updateTeam(user.getId(),id);
            return "加入队伍成功";
        }else{
            return "您已在一只队伍中，请先退出队伍";
        }
    }

}
