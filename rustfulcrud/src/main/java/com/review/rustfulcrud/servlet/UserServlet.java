package com.review.rustfulcrud.servlet;

import com.review.rustfulcrud.bean.User;
import com.review.rustfulcrud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServlet  {
    @Autowired
    UserMapper userMapper;

    public User getUser(String username){
        return userMapper.getUser(username);
    }

}
