package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Result<User> getUserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = this.findByUsername(username);
        return Result.success(user);
    }

    @Override
    public Result update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return Result.success();
    }

    @Override
    public Result updateAvatar(String url) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(url, id);
        return Result.success();
    }

    @Override
    public Result updatePwd(Map<String, String> paramMap) {
        String oldPwd = paramMap.get("old_pwd");
        String newPwd = paramMap.get("new_pwd");
        String rePwd = paramMap.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd)
                || !StringUtils.hasLength(newPwd)
                || !StringUtils.hasLength(rePwd)) {
            return Result.error("参数不能为空");
        }

        if (!newPwd.equals(rePwd)) {
            return Result.error("两次密码输入不一致");
        }

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = this.findByUsername(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误");
        }
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), user.getId());
        return Result.success();
    }
}
