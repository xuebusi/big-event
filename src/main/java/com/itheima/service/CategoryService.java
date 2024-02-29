package com.itheima.service;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;

import java.util.List;

public interface CategoryService {
    /**
     * 添加分类
     *
     * @param category 分类
     * @return result
     */
    Result add(Category category);

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    Result<List<Category>> list();
}
