package com.taro.springbootmall.service;

import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(int id);
    int createProduct(ProductRequest product);
    void updateProduct(int id, ProductRequest product);
    void deleteProduct(int id);
}
