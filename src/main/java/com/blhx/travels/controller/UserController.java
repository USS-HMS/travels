package com.blhx.travels.controller;

import com.blhx.travels.Utils.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
@CrossOrigin //允许跨域
public class UserController {
    @GetMapping("getImage")
    public void getImage( HttpServletResponse response, HttpSession session ) throws IOException{
        //获取验证码
        String securityCode=ValidateImageCodeUtils.getSecurityCode();
        //验证码存入session
        session.setAttribute("code",securityCode);
        //生成图片
        BufferedImage image=ValidateImageCodeUtils.createImage(securityCode);
        //响应浏览器
        response.setContentType("image/png");
        ImageIO.write(image,"png",response.getOutputStream());
    }
}
