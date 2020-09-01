package com.syclover.geekPlatform.mapper;

import com.syclover.geekPlatform.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Playwi0
 * @Data: 2020/8/12
 */
@Repository
public interface UserMapper {

    User getById(@Param("id") int id);
}
