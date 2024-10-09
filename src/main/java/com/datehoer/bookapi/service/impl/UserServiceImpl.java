package com.datehoer.bookapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datehoer.bookapi.mapper.UserMapper;
import com.datehoer.bookapi.model.User;
import com.datehoer.bookapi.model.UserVo;
import com.datehoer.bookapi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean checkUser(User user) {
        String username = user.getUsername();
        if (username != null && !username.isEmpty()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User selectOne = baseMapper.selectOne(queryWrapper);
            return selectOne == null;
        }
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        if (user != null) {
            return save(user);
        }
        return false;
    }

    @Override
    public User login(UserVo userVo){
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User selectOne = baseMapper.selectOne(queryWrapper);
        return selectOne.getPassword().equals(password) ? selectOne : null;
    }
}
