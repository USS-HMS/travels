package com.blhx.travels.service;

import com.blhx.travels.dao.UserDAO;
import com.blhx.travels.entity.User;
import com.blhx.travels.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register ( User user ){
        if (userMapper.findByUsername(user.getUsername())==null){
            userMapper.save(user);
        }else {
            throw new RuntimeException("用户名已经存在");
        }

    }
}
