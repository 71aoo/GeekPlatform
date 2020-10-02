package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    int addTeam(Team team);

    Team getTeamById(int id);

    int addTeamate(int id,String token);

    Team getTeamByName(String name);

    Team getTeamByToken(String token);

    List<Team> getAllName();

    Team isUserInTeam(String token, int teamID, int userID);

    List<Team> getAllTeam();
}
