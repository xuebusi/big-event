package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    /**
     * 添加用户
     *
     * @param username  用户名
     * @param md5String 密码
     */
    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{md5String}, now(), now())")
    void add(String username, String md5String);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id=#{id}")
    void update(User user);

    /**
     * 更新用户头像
     *
     * @param url 头像地址
     * @param id  用户ID
     */
    @Update("update user set user_pic=#{url}, update_time=now() where id=#{id}")
    void updateAvatar(String url, Integer id);
}
