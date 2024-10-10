package com.datehoer.bookapi.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.datehoer.bookapi.model.User;
import com.datehoer.bookapi.model.UserVo;
import com.datehoer.bookapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public static final int USERNAME_MIN_LENGTH = 6;
    public static final int USERNAME_MAX_LENGTH = 12;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 20;
    @PostMapping("/login")
    public SaResult  doLogin(@RequestBody UserVo user){
        if(user.getUsername()!=null && user.getPassword()!=null && !user.getUsername().isEmpty() && !user.getPassword().isEmpty()){
            user.setPassword(SaSecureUtil.md5(user.getPassword()));
            User loginUser = userService.login(user);
            if (loginUser == null) {
                return SaResult.error("登录失败");
            }else{
                StpUtil.login(loginUser.getId());
            }
            return SaResult.ok("登录成功");
        } else {
            return SaResult.error("登录失败");
        }
    }

    @PostMapping("/register")
    public SaResult doRegister(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if(username!=null && password!=null && email!=null && !username.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            if(!userService.checkUser(user)){
                return SaResult.error("注册失败");
            }
            if (username.length() < USERNAME_MIN_LENGTH || username.length() > USERNAME_MAX_LENGTH){
                return SaResult.error("用户名长度不符合要求");
            }
            if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH){
                return SaResult.error("密码长度不符合要求");
            }
            password = SaSecureUtil.md5(user.getPassword());
            user.setPassword(password);
            boolean registerStatus = userService.registerUser(user);
            if(!registerStatus){
                return SaResult.error("注册失败");
            }
            return SaResult.ok("注册成功");
        }else{
            return SaResult.error("注册失败");
        }
    }

    @RequestMapping("/logout")
    public SaResult doLogout() {
        StpUtil.logout();
        return SaResult.ok();
    }
}
