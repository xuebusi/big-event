package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;

public interface ArticleService {
    /**
     * 文章列表
     *
     * @param pageNum    当前页
     * @param pageSize   每页条数
     * @param categoryId 文章分类
     * @param state      发布状态
     * @return 分页结果
     */
    Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    /**
     * 文章详情
     *
     * @param id 文章ID
     * @return 详情
     */
    Result<Article> detail(String id);

    /**
     * 添加文章
     *
     * @param article 文章
     * @return result
     */
    Result add(Article article);

    /**
     * 修改文章
     *
     * @param article 文章
     * @return result
     */
    Result update(Article article);

    /**
     * 删除文章
     *
     * @param id 文章ID
     * @return result
     */
    Result delete(Integer id);
}
