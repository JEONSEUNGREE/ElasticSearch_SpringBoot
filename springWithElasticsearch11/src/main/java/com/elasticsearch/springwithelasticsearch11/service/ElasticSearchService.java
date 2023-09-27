package com.elasticsearch.springwithelasticsearch11.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.elasticsearch.springwithelasticsearch11.entity.Product;
import com.elasticsearch.springwithelasticsearch11.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Product> fuzzySearch(String approximateProductName) throws IOException {
        Supplier<Query>  supplier = ElasticSearchUtil.createSupplierQuery(approximateProductName);
        SearchResponse<Product> response = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch supplier fuzzy query " + supplier.get().toString());
        return response;
    }
    public SearchResponse<Product> autoSuggestProduct(String approximateProductName) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierAutoSuggest(approximateProductName);
        SearchResponse<Product> response = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch auto suggestion query " + supplier.get().toString());
        return response;
    }

    public SearchResponse<Product> boolQueryProducts(String productName, Integer qty) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierQueryForBoolQuery(productName, qty);
        SearchResponse<Product> response = elasticsearchClient
                .search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch bool query " + supplier.get().toString());
        return response;
    }
}
