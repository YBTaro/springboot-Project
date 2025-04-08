package com.taro.springbootmall.controller;

import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        int id = productService.createProduct(productRequest);
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
