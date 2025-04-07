package com.taro.springbootmall.dao;

import com.taro.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(int id);
}
