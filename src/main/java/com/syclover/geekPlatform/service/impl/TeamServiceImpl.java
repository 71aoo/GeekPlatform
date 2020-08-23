package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.TeamMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.service.TeamService;
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


    @Override
    public ResultT<Team> createTeam(Team team) {
        int result = teamMapper.addTeam(team);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    @Override
    public ResultT<Team> addTeamate(long id, String token) {
        int result = teamMapper.addTeamate(id, token);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),null);
        }
    }

    @Override
    public ResultT<Team> getTeam(long id) {
        Team team = teamMapper.getTeamById(id);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    @Override
    public ResultT<Team> getTeam(String name) {
        Team team = teamMapper.getTeamByName(name);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    @Override
    public ResultT<Team> getTeamByToken(String token) {
        Team team = teamMapper.getTeamByToken(token);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    @Override
    public ResultT<List<String>> getAllName() {
        List<Team> teamList = teamMapper.getAllName();
        List<String> names = new ArrayList<String>();
        for (Team team : teamList){
            names.add(team.getName());
        }
        return new ResultT<List<String>>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),names);
    }


}
