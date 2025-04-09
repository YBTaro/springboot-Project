package com.taro.springbootmall.service.impl;

import com.taro.springbootmall.dao.ProductDao;
import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
    @Override
    public int createProduct(ProductRequest product){
        return productDao.createProduct(product);
    }

    @Override
    public void updateProduct(int id, ProductRequest productRequest) {
        productDao.updateProduct(id, productRequest);
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }
}
