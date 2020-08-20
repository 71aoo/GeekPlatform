package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.CategoryMapper;
import com.syclover.geekPlatform.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/19
 */
@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void getAll(){

        List<Category> categories = categoryMapper.getAll();

        for (Category c : categories){
            System.out.println(c);
        }
    }
}
