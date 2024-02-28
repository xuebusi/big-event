package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result register(String username, String password) {
        // 查询用户名是否存在
        User u = this.findByUsername(username);
        if (u == null) {
            String md5String = Md5Util.getMD5String(password);
            userMapper.add(username, md5String);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @Override
    public Result<String> login(String username, String password) {
        User u = this.findByUsername(username);
        if (u == null) {
            return Result.error("用户名错误");
        }
        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            return Result.success("登录令牌。。。");
        }
        return Result.error("密码错误");
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
