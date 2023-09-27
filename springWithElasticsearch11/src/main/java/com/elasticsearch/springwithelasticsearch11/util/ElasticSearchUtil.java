package com.elasticsearch.springwithelasticsearch11.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> createSupplierQuery(String approximateProductName) {

        return () -> Query.of(q -> q.fuzzy(createFuzzyQuery(approximateProductName)));
    }

    public static Supplier<Query> createSupplierAutoSuggest(String partialProductName) {

        return () -> Query.of(q -> q.match(createAutoSuggestMatchQuery(partialProductName)));
    }

    public static Supplier<Query> createSupplierQueryForBoolQuery(String partialProductName, Integer qty) {

        return () -> Query.of(q -> q.bool(boolQuery(partialProductName, qty)));
    }

    public static FuzzyQuery createFuzzyQuery(String approximateProductName) {

        val fuzzyQuery = new FuzzyQuery.Builder();

        return fuzzyQuery.field("name").value(approximateProductName).build();
    }


    public static MatchQuery createAutoSuggestMatchQuery(String partialProductName) {
        val autoSuggestQuery = new MatchQuery.Builder();

        return autoSuggestQuery.field("name").query(partialProductName).analyzer("standard").build();
    }

    public static BoolQuery boolQuery(String productName, Integer qty) {

        val boolQuery = new BoolQuery.Builder();

        return boolQuery.filter(termQuery(productName)).must(matchQuery(qty)).build();
    }

    public static List<Query> matchQuery(Integer qty) {
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q -> q.match(matchQuery.field("qty").query(qty).build())));
        return matches;
    }

    public static List<Query> termQuery(String productName) {
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q -> q.term(termQuery.field("name").value(productName).build())));
        return terms;
    }
}
