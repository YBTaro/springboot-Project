package com.taro.springbootmall.dao.impl;

import com.taro.springbootmall.dao.ProductDao;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public Product getProductById(int id) {
        String sql = "SELECT product_id, product_name, category, img_url, price, stock, description, create_date, last_modified_date FROM product WHERE product_id = :product_id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product_id", id);

        List<Product> productList = jdbcTemplate.query(sql, params, new ProductRowMapper());
        if (productList != null && productList.size() > 0) { return productList.get(0); }
        return null;
    }
}
