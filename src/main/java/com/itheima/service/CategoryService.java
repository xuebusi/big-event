package com.itheima.service;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;

public interface CategoryService {
    /**
     * 添加分类
     *
     * @param category 分类
     * @return result
     */
    Result add(Category category);
}
