//package com.syclover.geekPlatform.mapper;
//
//import com.syclover.geekPlatform.entity.User;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
///**
// * @Author: Playwi0
// * @Data: 2020/8/12
// */
//@Repository
//public interface UserMapper {
//
//    @Select("select * from Users where id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "realName", column = "real_name"),
//            @Result(property = "studentId", column = "student_id"),
//            @Result(property = "isCuit", column = "is_cuit"),
//            @Result(property = "motto", column = "motto"),
//            @Result(property = "headerImg", column = "header_img"),
//            @Result(property = "isDel", column = "is_del"),
//            @Result(property = "teamId", column = "team_id"),
//            @Result(property = "createdTime", column = "created_time"),
//            @Result(property = "updatedTime", column = "updated_time")
//    })
//    User getById(@Param("id") long id);
//}
