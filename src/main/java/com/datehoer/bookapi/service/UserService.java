package com.datehoer.bookapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.datehoer.bookapi.model.User;
import com.datehoer.bookapi.model.UserVo;

public interface UserService extends IService<User>
{
    boolean checkUser(User user);

    boolean registerUser(User user);

    User login(UserVo userVo);
}