package com.blhx.travels.service;

import com.blhx.travels.dao.UserDAO;
import com.blhx.travels.entity.User;
import com.blhx.travels.mapper.UserMapper;
import com.blhx.travels.service.UserService;
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

    @Override
    public User login ( User user ){
       User usermp= userMapper.findByUsername(user.getUsername());
        if (usermp!=null){
            if (usermp.getPassword().equals(user.getPassword())){
                return usermp;
            }else {
                throw new RuntimeException("密码错误");
            }
        }else {
            throw new RuntimeException("用户名不正确");
        }
    }
}

