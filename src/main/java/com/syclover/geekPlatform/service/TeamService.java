package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;

import java.util.List;

public interface TeamService {

//    创建队伍
    ResultT<Team> createTeam(Team team);

//    加入队伍成员
    ResultT<Team> addTeamate(int id,String token);

//    使用id查找队伍
    ResultT<Team> getTeam(int id);

//    使用名称查找队伍
    ResultT<Team> getTeam(String name);

    ResultT<Team> getTeamByToken(String token);

    ResultT<List<String>> getAllName();
}
