package com.taro.springbootmall.service;

import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(int id);
    int createProduct(ProductRequest product);
}
