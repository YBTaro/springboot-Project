package com.taro.springbootmall.service;

import com.taro.springbootmall.dto.UserRegisterRequest;
import com.taro.springbootmall.model.User;

public interface UserService {
    public int register(UserRegisterRequest userRegisterRequest);
    public User getUserById(int id);
}
