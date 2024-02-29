package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class User {
    @NotNull(message = "用户ID不能为空")
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore
    private String password;//密码

    @NotEmpty(message = "昵称不能为空")
    @Pattern(regexp = "^\\S{1,10}$", message = "昵称必须是1-10位非空字符")
    private String nickname;//昵称

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "email必须符合邮箱格式")
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
