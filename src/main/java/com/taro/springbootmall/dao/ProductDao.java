package com.taro.springbootmall.dao;

import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(int id);
    int createProduct(ProductRequest product);
    void updateProduct(int id,ProductRequest productRequest);
    void deleteProduct(int id);
}
