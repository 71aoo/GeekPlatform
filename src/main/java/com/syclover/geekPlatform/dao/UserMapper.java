package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
@Repository
public interface UserMapper {

    User getUserById(long id);

    List<User> getAllUser();

}
