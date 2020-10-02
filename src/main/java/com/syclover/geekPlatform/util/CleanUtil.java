package com.syclover.geekPlatform.util;

import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;

/** 用户敏感信息设置
 * @Author cueyu
 * @Date 2020/10/2
 */

public class CleanUtil {
    public static User cleanUser(User user){
        if (user == null){
            return null;
        }
        user.setRealName(null);
        user.setStudentId(null);
        user.setEmail(null);
        user.setPassword(null);
        return user;
    }

    public static Team cleanTeam(Team team){
        User m1 = team.getMemberOne();
        User m2 = team.getMemberTwo();
        m1 = cleanUser(m1);
        m2 = cleanUser(m2);
        team.setMemberOne(m1);
        team.setMemberTwo(m2);
        team.setToken(null);
        return team;
    }
}
