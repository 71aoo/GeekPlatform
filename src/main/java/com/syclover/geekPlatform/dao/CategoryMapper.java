package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * @Author: Playwi0
 * @Data: 2020/8/17
 */
@Repository
public interface CategoryMapper {

    Category getById(long id);
}
