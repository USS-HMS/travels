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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
@CrossOrigin //允许跨域
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody User user,HttpServletRequest request){
        Result result=new Result();
        log.info("user"+user);
        try {
            User userDB=userService.login(user);
            //登陆成功后保存用户标记
            request.getServletContext().setAttribute(userDB.getId(),userDB);
            result.setMsg("登录成功").setUserId(userDB.getId());
        }catch (Exception e){
            result.setState(false).setMsg(e.getMessage());
        }

        return result;
    }
    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public Result register( String code, String key, @RequestBody User user , HttpServletRequest request ){
        Result result=new Result();
        log.info("接收到的验证码"+code);
        log.info("接收到的user对象"+user);
        String keyCode=(String)request.getServletContext().getAttribute(key);
        log.info(keyCode);
        try{
            if (code.equalsIgnoreCase(keyCode)){
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

     * @throws IOException
     */
    @GetMapping("getImage")
    @ResponseBody
    public Map<String,String> getImage(  HttpServletRequest request ) throws IOException{
        Map<String,String> result=new HashMap<>();
        CreateImageCode createImageCode=new CreateImageCode();
        //获取验证码
        String securityCode=createImageCode.getCode();
        //验证码存入session
        String key=new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date());
        request.getServletContext().setAttribute(key,securityCode);
        //生成图片
        BufferedImage image=createImageCode.getBuffImg();
        //经行base64编码
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ImageIO.write(image,"png",bos);
        String string=Base64Utils.encodeToString(bos.toByteArray());
        result.put("key",key);
        result.put("image",string);
        return result;
    }
}
