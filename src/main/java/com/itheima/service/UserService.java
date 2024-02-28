package com.itheima.service;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;

public interface UserService {
    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     */
    Result register(String username, String password);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Token令牌
     */
    Result<String> login(String username, String password);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 查询登录用户信息
     *
     * @param token 令牌
     * @return 用户信息
     */
    Result<User> getUserInfo(String token);
}
