package com.blhx.travels.service;

import com.blhx.travels.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User login(User user);

    void register(User user);
}
