package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
public interface UserService {

    ResultT<User> getUser(int id);

    int registerUser(User user);

    ResultT<User> getLoginUser(String username);

    int getTeamId(int id);

    int getTeamId(User user);

//    感觉合成在teamService里面会好一点 但这样需要teamService自动装配一个userMapper
    int updateTeam(int id,long teamid);

    //获取最后的id
    int getLastId();

    // 验证激活邮箱
    int activeEmail(String token);
}
