package com.blhx.travels.text;

import com.blhx.travels.TravelsApplication;
import com.blhx.travels.entity.User;
import com.blhx.travels.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=TravelsApplication.class)

public class TestUserService {
    @Autowired
    private UserService userService;
    @Test
    public void totest(){
        User user=new User();
        user.setUsername("ljh");
        user.setEmail("2810@qq.com");
        user.setPassword("111");
        userService.register(user);
    }
}
