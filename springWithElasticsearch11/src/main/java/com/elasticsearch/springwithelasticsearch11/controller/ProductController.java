package com.elasticsearch.springwithelasticsearch11.controller;

import com.elasticsearch.springwithelasticsearch11.entity.Product;
import com.elasticsearch.springwithelasticsearch11.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
        System.out.println("서비스 주소 productService : " + productService);
    }

    @GetMapping("/products")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }
}
