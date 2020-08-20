package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/17
 */
@Repository
public interface AuthorMapper {

    // 根据id获取出题人
    Author getById(long id);

    // 获得所有出题人
    List<Author> getAll();
}
