package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 分页查询
     *
     * @param userId     用户ID
     * @param categoryId 分类ID
     * @param state      发布状态
     * @return 当前页数据
     */
    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article detail(String id);

    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    @Update("update article set title=#{title}, content=#{content}, category_id=#{categoryId}, update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
