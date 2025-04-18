package com.taro.springbootmall.service.impl;

import com.taro.springbootmall.dao.UserDao;
import com.taro.springbootmall.dto.UserRegisterRequest;
import com.taro.springbootmall.model.User;
import com.taro.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
