package com.taro.springbootmall.service.impl;

import com.taro.springbootmall.dao.UserDao;
import com.taro.springbootmall.dto.UserLoginRequest;
import com.taro.springbootmall.dto.UserRegisterRequest;
import com.taro.springbootmall.model.User;
import com.taro.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public int register(UserRegisterRequest userRegisterRequest) {
        if(userDao.getUserByEmail(userRegisterRequest.getEmail())!= null){ // if the email has been registered
            logger.warn("The email address {} is already in use.", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes(StandardCharsets.UTF_8));
        userRegisterRequest.setPassword(hashedPassword);
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());



        if(user == null){
            logger.warn("The email address {} is not exist.", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else if(!user.getPassword().equals(userLoginRequest.getPassword())){
            String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes(StandardCharsets.UTF_8));
            if(!hashedPassword.equals(user.getPassword())){
                logger.warn("The password {} is not match.", userLoginRequest.getPassword());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

        }
        return user;
    }
}
