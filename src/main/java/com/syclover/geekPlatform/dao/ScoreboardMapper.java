package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import org.springframework.stereotype.Repository;

import java.io.ObjectInputStream;
import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/28
 */
@Repository
public interface ScoreboardMapper {

   List<Team> getTeamScoreboard();

   List<User> getUserScoreboard();

}
