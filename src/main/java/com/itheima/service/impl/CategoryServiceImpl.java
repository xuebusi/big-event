package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Result add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        category.setCreateUser(id);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.add(category);
        return Result.success();
    }

    @Override
    public Result<List<Category>> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> categories = categoryMapper.list(userId);
        return Result.success(categories);
    }

    @Override
    public Result<Category> detail(String id) {
        Category category = categoryMapper.findById(id);
        return Result.success(category);
    }

    @Override
    public Result update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
        return Result.success();
    }
}
