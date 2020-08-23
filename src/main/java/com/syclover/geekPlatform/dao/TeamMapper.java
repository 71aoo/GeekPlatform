package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    int addTeam(Team team);

    Team getTeamById(long id);

    int addTeamate(long id,String token);

    Team getTeamByName(String name);

    Team getTeamByToken(String token);

    List<Team> getAllName();
}