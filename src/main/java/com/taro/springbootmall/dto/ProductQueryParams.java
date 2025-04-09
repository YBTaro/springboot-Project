package com.taro.springbootmall.dto;

import com.taro.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    String search;
    ProductCategory category;

    public String getSearch() {
        return search;
    }

    public void setSearch(String name) {
        this.search = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
