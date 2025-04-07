package com.taro.springbootmall.rowmapper;

import com.taro.springbootmall.constant.ProductCategory;
import com.taro.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setPrice(rs.getInt("price"));
        product.setCategory(ProductCategory.valueOf(rs.getString("category")));
        product.setDesc(rs.getString("description"));
        product.setStock(rs.getInt("stock"));
        product.setImgUrl(rs.getString("img_url"));
        product.setCreateTime(rs.getTimestamp("create_date"));
        product.setUpdateTime(rs.getTimestamp("last_modified_date"));

        return product;
    }
}
