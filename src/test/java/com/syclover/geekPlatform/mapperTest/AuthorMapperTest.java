package com.syclover.geekPlatform.mapperTest;

import com.syclover.geekPlatform.dao.AuthorMapper;
import com.syclover.geekPlatform.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Playwi0
 * @Data: 2020/8/20
 */
@SpringBootTest
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;


    @Test
    public void getAll(){
        List<Author> all = authorMapper.getAll();
    }
}
