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

    /**
     * 创建队伍
     * @param teamName
     * @param img
     * @param motto
     * @param session
     * @return
     */
    @PostMapping("/create")
    public ResultT createTeam(String teamName,String img,String motto,HttpSession session){
        User user = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }
        if (!StringUtils.isEmpty(teamName)){
            if (bloomFilterService.contain(teamName)){
                return new ResultT(ResponseCode.TEAM_NAME_USED.getCode(),ResponseCode.TEAM_NAME_USED.getMsg(),null);
            }
            ResultT<Team> data = teamService.createTeam(teamName, img, motto, user);
            int teamId = data.getData().getId();
            int id = user.getId();
            userService.updateTeam(teamId,id);
            redisService.set(RedisUtil.generateTeamKey(data.getData().getId()),teamName);
            bloomFilterService.add(teamName);
            return data;
        }else{
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }

    }


    /**
     * 用户根据token加入队伍
     * @param token
     * @param session
     * @return
     */
    @PostMapping("/join")
    public ResultT joinTeam( String token,HttpSession session) {
        User user = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
        if (user.getTeamId() != 0){
            return new ResultT(ResponseCode.USER_HAS_IN_TEAM.getCode(),ResponseCode.USER_HAS_IN_TEAM.getMsg(),null);
        }
        Team team = teamService.getTeamByToken(token).getData();
        if (team != null){
            if (team.getMemberTwo() == null){
                teamService.addTeamate(user.getId(),token);
                userService.updateTeam(user.getId(),team.getId());
                Team data = teamService.getTeam(team.getId()).getData();
                return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),data);
            }else {
                return new ResultT(ResponseCode.TEAM_JOIN_FAILED.getCode(),ResponseCode.TEAM_JOIN_FAILED.getMsg(),null);
            }
        }
        else{
            return new ResultT(ResponseCode.TEAM_NOT_FOUND.getCode(),ResponseCode.TEAM_NOT_FOUND.getMsg(),null);
        }
    }

    /**
     * 更新接口
     * @param headerImg
     * @param motto
     * @param session
     * @return
     */
    @PostMapping("/update")
    public ResultT updateTeamInfo(String headerImg,String motto,HttpSession session){
        User teamMember = userService.getLoginUser(SessionGetterUtil.getUsername(session)).getData();
        return teamService.updateTeam(headerImg, motto, teamMember);
    }



    /**
     * 队伍名是否重复接口
     * @param name
     * @return
     */
    @PostMapping("/checkName")
    public ResultT checkNameVariable(String name){
        if(StringUtils.isEmpty(name)){
           return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        if (bloomFilterService.contain(name)){
            //名称已被注册
            return new ResultT(ResponseCode.NAME_HAVE_ERROR.getCode(),ResponseCode.NAME_HAVE_ERROR.getMsg(),null);
        }else {
            return new ResultT(ResponseCode.TEAM_NAME_VALID.getCode(),ResponseCode.TEAM_NAME_VALID.getMsg(),null);
        }
    }

    /**
     * 根据id查找队伍信息接口
     * @param id
     * @return
     */
    @GetMapping("/getInfoById")
    public ResultT getTeamById(Integer id){
        if(id == null){
            return new ResultT(ResponseCode.PARAMETER_MISS_ERROR.getCode(),ResponseCode.PARAMETER_MISS_ERROR.getMsg(),null);
        }
        return teamService.getTeam(id);
    }

    /**
     * 获取所有队伍以及队伍成员
     * 信息的接口
     * @return
     */
    @GetMapping("/getAll")
    public ResultT getAllTeam(){
        return teamService.getAllTeam();
    }

}
