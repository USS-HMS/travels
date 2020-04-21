package com.blhx.travels.mapper;

import com.blhx.travels.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into t_user values(#{id},#{username},#{password},#{email})")
    void save( User user );

    @Select("select * from t_user where username=#{username}")
    User findByUsername(String username);

}
