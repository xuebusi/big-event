package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;
    @NotEmpty(message = "分类别名不能为空")
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 添加分类接口参数校验分组
     */
    public interface Add extends Default {
    }

    /**
     * 修改分类接口参数校验分组
     */
    public interface Update extends Default {
    }
}
