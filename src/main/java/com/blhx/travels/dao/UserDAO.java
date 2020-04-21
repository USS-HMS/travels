package com.blhx.travels.dao;

import com.blhx.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    //根据username查找用户
    User findByUsername(String username);
    //注册用户
    void save( User user);
}
