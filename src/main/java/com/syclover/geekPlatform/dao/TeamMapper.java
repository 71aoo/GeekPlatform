package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    int addTeam(@Param("team") Team team);

    Team getTeamById(int id);

    int addTeamate(int id,String token);

    Team getTeamByName(String name);

    Team getTeamByToken(String token);

    List<Team> getAllName();

    Team isUserInTeam(String token, int teamID, int userID);

    List<Team> getAllTeam();

    Team getUserTeamById(int id);

    Team isContainName(String name);

    int updateTeamInfo(@Param("team") Team team);

    boolean updateTeamLastPointTime(int teamID);
}
