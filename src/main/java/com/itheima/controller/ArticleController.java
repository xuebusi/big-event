package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping
    public Result<PageBean<Article>> list(
            @RequestParam Integer pageNum, @RequestParam Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state) {
        return articleService.list(pageNum, pageSize, categoryId, state);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@NotNull String id) {
        return articleService.detail(id);
    }

    @PostMapping
    public Result add(@RequestBody @Validated(Article.Add.class) Article article) {
        return articleService.add(article);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Article.Update.class) Article article) {
        return articleService.update(article);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        return articleService.delete(id);
    }
}
