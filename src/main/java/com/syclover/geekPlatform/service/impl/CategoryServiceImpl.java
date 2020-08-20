package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.CategoryMapper;
import com.syclover.geekPlatform.entity.Category;
import com.syclover.geekPlatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /*
    * 查询所有分类
    * */
    @Override
    public ResultT<List<Category>> getAllCategory() {

        // 所有分类
        List<Category> categories = categoryMapper.getAll();

        // 成功
        ResultT<List<Category>> resultT = new ResultT<>();
        resultT.setStatus(ResponseCode.SUCCESS.getCode());
        resultT.setMsg(ResponseCode.SUCCESS.getMsg());
        resultT.setData(categories);

        return resultT;
    }

    /*
    * 根据id查询
    * */
    @Override
    public ResultT<Category> getCategoryByID(int id) {

        if (id < 0){

            return new ResultT<Category>(ResponseCode.PARAMETER_ERROR.getCode(),
                    ResponseCode.PARAMETER_ERROR.getMsg(), null);
        }

        Category category = categoryMapper.getById(id);

        ResultT<Category> resultT = new ResultT<>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                category);

        return resultT;
    }


}
