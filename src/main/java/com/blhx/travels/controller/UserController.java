package com.blhx.travels.controller;

import com.blhx.travels.Utils.CreateImageCode;
import com.blhx.travels.Utils.ValidateImageCodeUtils;
import com.blhx.travels.entity.Result;
import com.blhx.travels.entity.User;
import com.blhx.travels.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
@CrossOrigin //允许跨域
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param code
     * @param user
     * @param session
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public Result register( String code, @RequestBody User user , HttpSession session){
        Result result=new Result();
        log.info("接收到的验证码"+code);
        log.info("接收到的user对象"+user);
        String sessionCode=(String) session.getAttribute("code");
        log.info(sessionCode);
        try{
            if (code.equalsIgnoreCase(sessionCode)){
//        注册用户
                userService.register(user);
                result.setMsg("注册成功!!");
            }else{
                throw new RuntimeException("验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
            return result;
    }
    /**
     * 生成验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("getImage")
    public void getImage( HttpServletResponse response, HttpSession session ) throws IOException{
        CreateImageCode createImageCode=new CreateImageCode();
        //获取验证码
        String securityCode=createImageCode.getCode();
        //验证码存入session
        session.setAttribute("code",securityCode);
        //生成图片
        BufferedImage image=createImageCode.getBuffImg();
        //响应浏览器
        response.setContentType("image/png");
        ImageIO.write(image,"png",response.getOutputStream());
    }
}
