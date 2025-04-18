package com.taro.springbootmall.dto;

import com.taro.springbootmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
    @NotNull
    private String name;
    private String desc;
    @NotNull
    private int price;
    @NotNull
    private int stock;
    @NotNull
    private String imgUrl;
    @NotNull
    ProductCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
