package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.register(username, password);
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.login(username, password);
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        return userService.update(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @NotEmpty @URL String avatarUrl) {
        return userService.updateAvatar(avatarUrl);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> map) {
        return userService.updatePwd(map);
    }
}
