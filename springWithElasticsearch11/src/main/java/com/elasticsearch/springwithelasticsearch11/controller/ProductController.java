package com.elasticsearch.springwithelasticsearch11.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.elasticsearch.springwithelasticsearch11.entity.Product;
import com.elasticsearch.springwithelasticsearch11.service.ElasticSearchService;
import com.elasticsearch.springwithelasticsearch11.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/apis")
public class ProductController {

    private final ProductService productService;

    private ElasticSearchService elasticSearchService;

    @Autowired
    public ProductController(ProductService productService, ElasticSearchService elasticSearchService) {
        this.productService = productService;
        this.elasticSearchService = elasticSearchService;
        System.out.println("서비스 주소 productService : " + productService);
    }

    @GetMapping("/findAll")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @GetMapping("/fuzzySearch/{approximateProductName}")
    public List<Product> fuzzySearch (@PathVariable String approximateProductName) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.fuzzySearch(approximateProductName);
        List<Hit<Product>> hitList = searchResponse.hits().hits();
        System.out.println(hitList);

        List<Product> productList = new ArrayList<>();

        hitList.forEach(item -> productList.add(item.source()));

        return productList;
    }

}
