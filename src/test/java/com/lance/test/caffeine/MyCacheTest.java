package com.lance.test.caffeine;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyCacheTest {

    @Test
    public void testLocalCache() {
        Cache<String, Object> cache = Caches.<String, Object>newBuilder().build();
        System.out.println(cache.get("name"));
        cache.put("name", "Lance");
        System.out.println(cache.get("name"));
    }


    @Test
    public void testLoadingCache() {
        Map<String, Object> data = new HashMap<>();
        data.put("age", 996);

        Cache<String, Object> cache = Caches.<String, Object>newBuilder()
                .writer(data::put)
                .loader(data::get)
                .build();

        System.out.println(cache.get("name"));
        System.out.println(cache.get("age"));
        cache.put("name", "Lance");
        System.out.println(cache.get("name"));

        System.out.println(data);
    }
}
