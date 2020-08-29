package com.syclover.geekPlatform.dao;

import com.syclover.geekPlatform.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/17
 */
@Repository
public interface CategoryMapper {

    // 根据id查询某个分类
    Category getById(int id);

    // 获取所有分类
    List<Category> getAll();

    // 添加一个分类
    boolean addCategory(String name);
}
