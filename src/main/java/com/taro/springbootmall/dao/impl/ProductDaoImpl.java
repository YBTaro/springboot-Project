package com.taro.springbootmall.dao.impl;

import com.taro.springbootmall.dao.ProductDao;
import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.rowmapper.ProductRowMapper;
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

    @Override
    public int createProduct(ProductRequest product) {
        String sql = "INSERT INTO product(product_name, category, img_url, price, stock, description, create_date, last_modified_date) VALUES (:product_name, :category, :img_url, :price, :stock, :description, :create_date, :last_modified_date)";

        Map<String, Object> params = new HashMap<>();
        params.put("product_name", product.getName());
        params.put("category", product.getCategory().name());
        params.put("img_url", product.getImgUrl());
        params.put("price", product.getPrice());
        params.put("stock", product.getStock());
        params.put("description", product.getDesc());

        Date now = new Date();
        params.put("create_date", now);
        params.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateProduct(int id, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :product_name, category = :category, img_url = :imgUrl, price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate WHERE product_id = :product_id";
        Map<String, Object> params = new HashMap<>();
        params.put("product_id", id);
        params.put("product_name", productRequest.getName());
        params.put("category", productRequest.getCategory().name());
        params.put("imgUrl", productRequest.getImgUrl());
        params.put("price", productRequest.getPrice());
        params.put("stock", productRequest.getStock());
        params.put("description", productRequest.getDesc());
        params.put("lastModifiedDate", new Date());
        jdbcTemplate.update(sql, params);

    }
}
