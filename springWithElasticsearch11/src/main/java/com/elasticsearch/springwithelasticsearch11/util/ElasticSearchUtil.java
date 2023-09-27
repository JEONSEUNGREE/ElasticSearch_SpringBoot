package com.elasticsearch.springwithelasticsearch11.util;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> createSupplierQuery(String approximateProductName) {

        return () -> Query.of(q -> q.fuzzy(createFuzzyQuery(approximateProductName)));
    }

    public static Supplier<Query> createSupplierAutoSuggest(String partialProductName) {

        return () -> Query.of(q -> q.match(createAutoSuggestMatchQuery(partialProductName)));
    }

    public static FuzzyQuery createFuzzyQuery(String approximateProductName) {

        val fuzzyQuery = new FuzzyQuery.Builder();

        return fuzzyQuery.field("name").value(approximateProductName).build();
    }


    public static MatchQuery createAutoSuggestMatchQuery(String partialProductName) {
        val autoSuggestQuery = new MatchQuery.Builder();

        return autoSuggestQuery.field("name").query(partialProductName).analyzer("standard").build();
    }
}
