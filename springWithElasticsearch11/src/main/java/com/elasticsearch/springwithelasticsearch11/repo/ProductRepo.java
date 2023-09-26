package com.elasticsearch.springwithelasticsearch11.repo;

import com.elasticsearch.springwithelasticsearch11.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product, Integer> {
}
