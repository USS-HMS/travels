package com.blhx.travels.text;

import com.blhx.travels.TravelsApplication;
import com.blhx.travels.entity.Province;
import com.blhx.travels.entity.User;
import com.blhx.travels.service.ProvinceService;
import com.blhx.travels.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes=TravelsApplication.class)

public class TestProvinceService {
    @Autowired
    private ProvinceService provinceService;
    @Test
    public void totest(){
        List<Province> page=provinceService.findByPage(1, 5);

    }
}
