package com.taro.springbootmall.dao;

import com.taro.springbootmall.dto.ProductQueryParams;
import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    int countProducts(ProductQueryParams params);
    Product getProductById(int id);
    List<Product> getAllProduct(ProductQueryParams params);
    int createProduct(ProductRequest product);
    void createProducts(List<ProductRequest> productList);
    void updateProduct(int id,ProductRequest productRequest);
    void deleteProduct(int id);
}
