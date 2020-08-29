package com.syclover.geekPlatform.controller;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/29
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 返回所有分类信息
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public ResultT getAllCategories(){

        ResultT<List<Category>> resultT = categoryService.getAllCategory();

        return resultT;
    }


    /**
     * 添加一个 category
     * @param name
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultT addCategory(String name){

        ResultT resultT = categoryService.addCategory(name);

        return resultT;
    }

    /**
     * 根据 id 获取 category 信息
     * @param categoryID
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResultT getByID(int categoryID){

        ResultT<Category> resultT = categoryService.getCategoryByID(categoryID);

        return resultT;
    }
}
