package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);
}
