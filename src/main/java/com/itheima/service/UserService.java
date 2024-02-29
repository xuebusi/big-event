package com.itheima.service;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;

import java.util.Map;

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
     * @return 用户信息
     */
    Result<User> getUserInfo();

    /**
     * 更新用户信息
     *
     * @param user 用户
     */
    void update(User user);

    /**
     * 更新用户头像
     *
     * @param url 头像地址
     */
    void updateAvatar(String url);

    /**
     * 更新用户密码
     *
     * @param paramMap 参数
     * @return result
     */
    Result updatePwd(Map<String, String> paramMap);
}
