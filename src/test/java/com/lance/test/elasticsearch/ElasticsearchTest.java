package com.lance.test.elasticsearch;

import com.google.gson.Gson;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticsearchTest {

    private Client client = null;
    private String hostname = "192.168.147.100";
    private int port = 9300;
    private String indices = "trader";
    private String types = "data";

    @Before
    public void before() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
//                .put("client.transport.sniff", true)
                .put("cluster.name", "my-elastic").build();
        InetAddress address = InetAddress.getByName(hostname);
        client = new TransportClient.Builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(address, port));
    }

    @Test
    public void testCount() {
        //ES1
//        CountResponse countResponse = client.prepareCount()
//                .setIndices(indices)
//                .setTypes(types)
//                .execute()
//                .actionGet();
//        System.out.println(countResponse.getCount());

        //ES2
        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .execute().actionGet();
        System.out.println("Doc count: " + response.getHits().getTotalHits());
    }

    @Test
    public void testPost() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject()
                .field("name", "mouse")
                .field("count", "13")
                .field("length", "5")
                .endObject();

        IndexResponse response = client.prepareIndex(indices, types)
                .setSource(builder)
                .execute().actionGet();
        System.out.println(response.toString());
    }

    @Test
    public void testDelete() throws IOException {
        DeleteResponse response = client.prepareDelete(indices, types, "AVqXDz9ZZRh0caLqcCU9")
                .execute().actionGet();
        System.out.println(response.isFound());
    }

    @Test
    public void testPut() throws Exception {
        UpdateResponse response = client.prepareUpdate(indices, types, "1")
                .setDoc("count", "11")
                .execute().actionGet();
        System.out.println(response.getGetResult());
    }

    @Test
    public void testGet() throws Exception {
        GetResponse response = client.prepareGet(indices, types, "1")
                .execute().actionGet();
        System.out.println(response.getSourceAsString());
//        for (GetField field : response) {
//            System.out.println(field.getName() + ": " + field.getValue());
//        }
    }

    @Test
    public void testSearch() throws Exception {
        QueryBuilder builder = new MatchAllQueryBuilder();
        SearchResponse response = client.prepareSearch(indices).setTypes(types)
                .setQuery(builder)
                .execute().actionGet();

//        System.out.println(response.toString());
        for (SearchHit hit : response.getHits()) {
//            System.out.println(hit.getSourceAsString());
            System.out.println(hit.getSource().get("name"));
        }
    }

    @Test
    public void testTermsAgg() {

//        SumBuilder sumAgg = AggregationBuilders
//                .sum("val_sum")
//                .field("volume");
//        AggregationBuilder dateAgg = AggregationBuilders
//                .dateHistogram("date_hist")
//                .field("date")
//                .subAggregation(sumAgg);

        AggregationBuilder termsAgg = AggregationBuilders
                .terms("tt").field("tags").size(30);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .addAggregation(termsAgg)
                .execute().actionGet();

        SearchHits hits = response.getHits();
        Terms agg = response.getAggregations().get("tt");
        System.out.println(agg.getBuckets().size());
        for (Terms.Bucket entry : agg.getBuckets()) {
            String key = (String) entry.getKey(); // bucket key
            long docCount = entry.getDocCount(); // Doc count
            System.out.println("key " + key + " doc_count " + docCount);

            // We ask for top_hits for each bucket
//            TopHits topHits = entry.getAggregations().get("top");
//            for (SearchHit hit : topHits.getHits().getHits()) {
//                System.out.println(" -> id " + hit.getId() + " _source [{}]"
//                        + hit.getSource().get("category_name"));
//                ;
//            }
        }
        System.out.println(hits.getTotalHits());
//        int temp = 0;
//        for (int i = 0; i < hits.getHits().length; i++) {
//            // System.out.println(hits.getHits()[i].getSourceAsString());
//            System.out.print(hits.getHits()[i].getSource().get("product_id"));
//            // if(orderfield!=null&&(!orderfield.isEmpty()))
//            // System.out.print("\t"+hits.getHits()[i].getSource().get(orderfield));
//            System.out.print("\t"
//                    + hits.getHits()[i].getSource().get("category_id"));
//            System.out.print("\t"
//                    + hits.getHits()[i].getSource().get("category_name"));
//            System.out.println("\t"
//                    + hits.getHits()[i].getSource().get("name"));
//        }
    }

    @Test
    public void testDateHistAgg() {
        long startTime = 1199145600000L;
        long endTime = 1490630400000L;
        String tag = "java";

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("tags", tag))
                .must(QueryBuilders.rangeQuery("date")
                        .from(startTime)
                        .to(endTime)
                        .includeLower(true)
                        .includeUpper(true));

        SumBuilder sumAgg = AggregationBuilders
                .sum("val_sum")
                .field("volume");
        AggregationBuilder dateAgg = AggregationBuilders
                .dateHistogram("date_hist")
                .field("date")
                .interval(DateHistogramInterval.MONTH)
                .minDocCount(0)
                .extendedBounds(startTime, endTime)
                .subAggregation(sumAgg);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .setQuery(queryBuilder)
                .addAggregation(dateAgg)
                .execute().actionGet();

//        Terms dAgg = response.getAggregations().get("date_hist");
        InternalHistogram internalHistogram = response.getAggregations().get("date_hist");

        System.out.println(internalHistogram.getBuckets().size());
        for (Object entry : internalHistogram.getBuckets()) {
//            System.out.println(new Gson().toJson(entry));
            InternalHistogram.Bucket e = (InternalHistogram.Bucket) entry;
//            print(((DateTime) e.getKey()).getMillis() + " ");

            for (Aggregation agg : e.getAggregations()) {
                InternalSum iSum = (InternalSum) agg;
//                println(new Gson().toJson(agg));
//                println(agg.getProperty());
//                println((int) iSum.getValue());
                System.out.print((int) iSum.getValue() + ",");
////                SumAggregator sA = (SumAggregator)agg;
//
////                System.out.println(metaData.get("sum"));
////                println(new Gson().toJson(metaData));
//                print(agg.getName() );
//                println(agg.getProperty("name"));
//                println(agg.getProperty("sum"));
            }
        }
    }

    @Test
    public void testSumAgg() {
        long startTime = 1459008000000L;
        long endTime = 1490544000000L;
        String tag = "java";

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("tags", tag))
                .must(QueryBuilders.rangeQuery("date")
                        .from(startTime)
                        .to(endTime)
                        .includeLower(true)
                        .includeUpper(true));

        SumBuilder sumAgg = AggregationBuilders
                .sum("sumA")
                .field("emotion");
        AggregationBuilder dateAgg = AggregationBuilders
                .dateHistogram("date_hist")
                .field("date")
                .interval(DateHistogramInterval.MONTH)
                .minDocCount(0)
                .extendedBounds(startTime, endTime)
                .subAggregation(sumAgg);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .setQuery(queryBuilder)
                .addAggregation(dateAgg)
                .execute().actionGet();

        InternalHistogram internalHistogram = response.getAggregations().get("date_hist");

        System.out.println(internalHistogram.getBuckets().size());
        for (Object entry : internalHistogram.getBuckets()) {
            System.out.println(new Gson().toJson(entry));
            InternalHistogram.Bucket e = (InternalHistogram.Bucket) entry;
            System.out.print(((DateTime) e.getKey()).getMillis() + " ");
            for (Aggregation agg : e.getAggregations()) {
                InternalSum iSum = (InternalSum) agg;
                System.out.println((int) iSum.getValue());
            }
        }
    }

    @Test
    public void testNaN() {
        long startTime = 1459008000000L;
        long endTime = 1490544000000L;
        String tag = "python";

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("tags", tag))
                .must(QueryBuilders.rangeQuery("date")
                        .from(startTime)
                        .to(endTime)
                        .includeLower(true)
                        .includeUpper(true))
                .mustNot(QueryBuilders.termQuery("emotion", "NaN"));

        SumBuilder sumAgg = AggregationBuilders
                .sum("sumA")
                .field("emotion");
        AggregationBuilder dateAgg = AggregationBuilders
                .dateHistogram("date_hist")
                .field("date")
                .interval(DateHistogramInterval.MONTH)
                .minDocCount(0)
                .extendedBounds(startTime, endTime)
                .subAggregation(sumAgg);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .setQuery(queryBuilder)
                .addAggregation(dateAgg)
                .execute().actionGet();

        InternalHistogram internalHistogram = response.getAggregations().get("date_hist");

        System.out.println(internalHistogram.getBuckets().size());
        for (Object entry : internalHistogram.getBuckets()) {
//            System.out.println(new Gson().toJson(entry));
            InternalHistogram.Bucket e = (InternalHistogram.Bucket) entry;
            System.out.print(((DateTime) e.getKey()).getMillis() + " ");
            for (Aggregation agg : e.getAggregations()) {
                InternalSum iSum = (InternalSum) agg;
                System.out.println(iSum.getValue());
            }
        }
    }


    @Test
    public void testTermAndSum() {
//        long startTime = 1456761600000L;
//        long endTime = 1459440000000L;

        QueryBuilder queryBuilder2016 = QueryBuilders.boolQuery()
//                .must(QueryBuilders.rangeQuery("date")
//                        .from(startTime)
//                        .to(endTime)
//                        .includeLower(true)
//                        .includeUpper(true))
//                .must(QueryBuilders.rangeQuery("emotion")
//                        .to(0.0))
//                .mustNot(QueryBuilders.termQuery("emotion", "NaN"))
                ;

        SumBuilder sumAgg = AggregationBuilders
                .sum("sumA")
                .field("volume");

        AggregationBuilder termAgg = AggregationBuilders
                .terms("terms")
                .field("tags")
                .subAggregation(sumAgg)
                .size(200);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
                .setQuery(queryBuilder2016)
                .addAggregation(termAgg)
                .execute().actionGet();

        Terms terms = response.getAggregations().get("terms");
        for (Terms.Bucket entry : terms.getBuckets()) {
//            println(new Gson().toJson(entry));
            System.out.print(entry.getKey() + " ");
            InternalSum sum = entry.getAggregations().get("sumA");
            System.out.println((int) sum.getValue());
        }

    }

    @Test
    public void testTerm() {
//        long time201603 = 1456934400000L;
//        long time201604 = 1459440000000L;
//        long time201703 = 1488297600000L;
//        long time201704 = 1490976000000L;

        long startTime = 1456761600000L;
        long endTime = 1459440000000L;

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("date")
                        .from(startTime)
                        .to(endTime)
                        .includeLower(true)
                        .includeUpper(true));

        AggregationBuilder termAgg = AggregationBuilders
                .terms("terms")
                .field("tags")
                .size(200);

        SearchResponse response = client.prepareSearch(indices)
                .setTypes(types)
//                .setQuery(queryBuilder)
                .addAggregation(termAgg)
                .execute().actionGet();

        Terms terms = response.getAggregations().get("terms");
        for (Terms.Bucket entry : terms.getBuckets()) {
            System.out.println(entry.getKey() + " " + entry.getDocCount());
        }
    }

    @After
    public void after() {
        client.close();
    }
}
