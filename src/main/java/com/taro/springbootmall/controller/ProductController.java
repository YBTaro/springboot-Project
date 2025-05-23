package com.taro.springbootmall.controller;

import com.taro.springbootmall.constant.ProductCategory;
import com.taro.springbootmall.dto.ProductQueryParams;
import com.taro.springbootmall.dto.ProductRequest;
import com.taro.springbootmall.model.Product;
import com.taro.springbootmall.service.ProductService;
import com.taro.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Validated
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

    @GetMapping("products")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            // sorting
            @RequestParam(defaultValue = "create_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) int limit,
            @RequestParam(defaultValue = "0") @Min(0) int offset) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        List<Product> productList = productService.getAllProduct(productQueryParams);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setList(productList);
        page.setTotal(productService.countProducts(productQueryParams));
        return ResponseEntity.ok(page);

    }

    @PostMapping("products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        int id = productService.createProduct(productRequest);
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PostMapping("products/batch")
    public ResponseEntity<?> createProducts(@RequestBody @Valid List<ProductRequest> productRequests) {
        productService.createProducts(productRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }


    @PutMapping("products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}

        productService.updateProduct(productId, productRequest);
        Product update_product = productService.getProductById(productId);
        return ResponseEntity.ok(update_product);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
