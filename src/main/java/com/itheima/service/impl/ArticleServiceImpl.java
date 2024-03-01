package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // PageHelper.startPage() 后直接跟查询语句
        List<Article> list = articleMapper.list(userId, categoryId, state);
        // 使用PageInfo包装查询列表
        PageInfo<Article> p = new PageInfo<>(list);
        // 返回自定义分页对象
        PageBean<Article> pb = new PageBean<>();
        pb.setTotal(p.getTotal());
        pb.setItems(p.getList());
        return Result.success(pb);
    }

    @Override
    public Result<Article> detail(String id) {
        Article article = articleMapper.detail(id);
        return Result.success(article);
    }

    @Override
    public Result add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.add(article);
        return Result.success();
    }

    @Override
    public Result update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
        return Result.success();
    }

    @Override
    public Result delete(Integer id) {
        articleMapper.delete(id);
        return Result.success();
    }
}
