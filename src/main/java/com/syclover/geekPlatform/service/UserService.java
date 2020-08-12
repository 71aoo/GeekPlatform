package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.User;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
public interface UserService {

    ResultT<User> getUser(long id);
}
