package com.example.testjdbc.dao;


import com.example.testjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User row);

    User selectByPrimaryKey(Integer id);

    @Select("select * from user where truename=#{trueName} order by id")
    List<User> selectByUserName(String trueName);

    List<User> selectAll();

    int updateByPrimaryKey(User row);
}