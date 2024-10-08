package com.datehoer.bookapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.datehoer.bookapi.model.UserVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public SaResult  doLogin(@RequestBody UserVo user){
        if("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())){
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        } else {
            return SaResult.error("登录失败");
        }
    }

    @RequestMapping("/logout")
    public SaResult doLogout() {
        StpUtil.logout();
        return SaResult.ok();
    }
}
