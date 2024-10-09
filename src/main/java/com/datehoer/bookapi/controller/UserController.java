package com.datehoer.bookapi.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.datehoer.bookapi.model.User;
import com.datehoer.bookapi.model.UserVo;
import com.datehoer.bookapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
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
        if(user.getUsername()!=null && user.getPassword()!=null && user.getEmail()!=null && !user.getUsername().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty()){
            if(!userService.checkUser(user)){
                return SaResult.error("注册失败");
            }
            String password = SaSecureUtil.md5(user.getPassword());
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
