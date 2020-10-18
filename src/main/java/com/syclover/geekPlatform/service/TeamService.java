package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TeamService {

//    创建队伍
    ResultT<Team> addTeam(Team team);

//    加入队伍成员
    ResultT<Team> addTeamate(int id,String token);

//    使用id查找队伍
    ResultT<Team> getTeam(int id);

//    使用名称查找队伍
    ResultT<Team> getTeam(String name);

    ResultT<Team> getTeamByToken(String token);

    ResultT<List<String>> getAllName();

    // 检查用户是否在队伍里面
    ResultT<Team> checkUserInTeam(String token, int teamID, int userID);

    ResultT<List<Team>> getAllTeam(int page);

    ResultT<Team> isContainName(String name);

    ResultT<Team> createTeam(String teamName, String img, String motto, User user);

    ResultT<Team> updateTeam(String headerImg,String motto,User teamMember);

    ResultT checkCuit(User user);

    ResultT getData(User user);

    ResultT<Team> getTeamWithToken(int id);
}
