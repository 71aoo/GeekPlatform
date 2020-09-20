package com.syclover.geekPlatform.service;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Category;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
public interface CategoryService {

    // 查询所有分类
    ResultT<List<Category>> getAllCategory();

    // 根据id查询分类
    ResultT<Category> getCategoryByID(int id);

    // 添加一个 category
    ResultT addCategory(String name);
}
