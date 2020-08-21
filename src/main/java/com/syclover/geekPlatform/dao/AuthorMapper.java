package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Author;
import org.springframework.stereotype.Repository;

/**
 * @Author: Playwi0
 * @Data: 2020/8/17
 */
@Repository
public interface AuthorMapper {

    Author getById(long id);
}
