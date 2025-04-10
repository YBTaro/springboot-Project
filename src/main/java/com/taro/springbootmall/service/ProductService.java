package com.taro.springbootmall.service;

import com.taro.springbootmall.dto.ProductQueryParams;
import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    int countProducts(ProductQueryParams params);
    Product getProductById(int id);
    List<Product> getAllProduct(ProductQueryParams productQueryParams);
    int createProduct(ProductRequest product);
    void createProducts(List<ProductRequest> products);
    void updateProduct(int id, ProductRequest product);
    void deleteProduct(int id);
}
