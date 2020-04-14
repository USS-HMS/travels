package com.blhx.travels.dao;

import com.blhx.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    void save( User user);
}
