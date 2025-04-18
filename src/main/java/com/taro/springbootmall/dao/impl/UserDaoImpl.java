package com.taro.springbootmall.dao.impl;

import com.taro.springbootmall.dao.UserDao;
import com.taro.springbootmall.dto.UserRegisterRequest;
import com.taro.springbootmall.model.User;
import com.taro.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO `user`(email, password, created_date, last_modified_date) VALUES(:email,:password,:created_date,:last_modified_date)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", userRegisterRequest.getEmail());
        params.put("password", userRegisterRequest.getPassword());
        Date now = new Date();
        params.put("created_date", now);
        params.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date  FROM `user` WHERE user_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<User> list = jdbcTemplate.query(sql, params, new UserRowMapper());
        if (list != null && list.size() > 0) { return list.get(0); }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date  FROM `user` WHERE email = :email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<User> list = jdbcTemplate.query(sql, params, new UserRowMapper());
        if(list != null && list.size() > 0) { return list.get(0); }
        return null;
    }
}
