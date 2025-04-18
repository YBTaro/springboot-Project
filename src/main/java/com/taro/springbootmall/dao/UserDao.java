package com.taro.springbootmall.dao;

import com.taro.springbootmall.dto.UserRegisterRequest;
import com.taro.springbootmall.model.User;

public interface UserDao {
    public int createUser(UserRegisterRequest userRegisterRequest);
    public User getUserById(int id);
}
