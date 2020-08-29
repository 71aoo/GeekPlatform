package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;

import java.util.List;

public interface TeamService {

//    创建队伍
    ResultT<Team> createTeam(Team team);

//    加入队伍成员
    ResultT<Team> addTeamate(long id,String token);

//    使用id查找队伍
    ResultT<Team> getTeam(long id);

//    使用名称查找队伍
    ResultT<Team> getTeam(String name);

    ResultT<Team> getTeamByToken(String token);

    ResultT<List<String>> getAllName();

    // 检查用户是否在队伍里面
    ResultT<Team> checkUserInTeam(String token, int teamID, int userID);
}
