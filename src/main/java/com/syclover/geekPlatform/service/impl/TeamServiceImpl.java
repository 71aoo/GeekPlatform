package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.TeamMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.util.CleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

//  创建团队
    @Override
    public ResultT<Team> createTeam(Team team) {
        int result = teamMapper.addTeam(team);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            Team newTeam = teamMapper.getTeamByToken(team.getToken());
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),newTeam);
        }
    }

//    根据队伍token来加入用户id
    @Override
    public ResultT<Team> addTeamate(int id, String token) {
        int result = teamMapper.addTeamate(id, token);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),null);
        }
    }

//    根据id得到队伍对象
    @Override
    public ResultT<Team> getTeam(int id) {
        Team team = teamMapper.getTeamById(id);
        if (team == null){
            return new ResultT<>(ResponseCode.TEAM_NOT_FOUND.getCode(),ResponseCode.TEAM_NOT_FOUND.getMsg(),null);
        }else {
            team = CleanUtil.cleanTeam(team);
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

//    根据队名得到队伍对象
    @Override
    public ResultT<Team> getTeam(String name) {
        Team team = teamMapper.getTeamByName(name);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

//    根据token得到队伍对象
    @Override
    public ResultT<Team> getTeamByToken(String token) {
        Team team = teamMapper.getTeamByToken(token);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

//    得到所有队伍名字
    @Override
    public ResultT<List<String>> getAllName() {
        List<Team> teamList = teamMapper.getAllName();
        List<String> names = new ArrayList<String>();
        for (Team team : teamList){
            names.add(team.getName());
        }
        return new ResultT<List<String>>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),names);
    }

    @Override
    public ResultT<Team> checkUserInTeam(String token, int teamID, int userID) {

        // 参数验证
        if (token == null || teamID < 0 || userID < 0){

            return new ResultT<Team>(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getMsg(), null);
        }

        // 数据库中取
        Team userInTeam = teamMapper.isUserInTeam(token, teamID, userID);

        if (userInTeam == null){

            return  new ResultT<Team>(ResponseCode.USER_NOT_IN_TEAM.getCode(), ResponseCode.USER_NOT_IN_TEAM.getMsg(), null);
        }

        // 成功返回
        return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), userInTeam);
    }

    @Override
    public ResultT<List<Team>> getAllTeam() {
        List<Team> allTeam = teamMapper.getAllTeam();
        List<Team> cleanTeams = new ArrayList<>();
        for (Team team : allTeam){
            Team cleanTeam = CleanUtil.cleanTeam(team);
            cleanTeams.add(team);
        }
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cleanTeams);
    }


}
