package com.review.rustfulcrud.mapper;

import com.review.rustfulcrud.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT*FROM`user`WHERE`userName`=#{userName}")
    public User getUser(String userName);
}
