package com.elasticsearch.springwithelasticsearch11.service;


import com.elasticsearch.springwithelasticsearch11.entity.Product;
import com.elasticsearch.springwithelasticsearch11.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product insertProduct(Product product) {
        return productRepo.save(product);
    }
}
