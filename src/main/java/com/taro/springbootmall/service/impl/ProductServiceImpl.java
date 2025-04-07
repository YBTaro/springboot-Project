package com.taro.springbootmall.service.impl;

import com.taro.springbootmall.dao.ProductDao;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
