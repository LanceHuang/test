package com.lance.test.cache;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * FIFO -> LRU -> LFU
 *
 * @author Lance
 */
public class LruCacheTest {

    private Map<String, Object> cache = new LruCache<>(3);

    @Test
    public void test() {
        cache.put("name1", "Lance1");
        cache.put("name2", "Lance2");
        cache.put("name3", "Lance3");
        cache.put("name4", "Lance4");
        System.out.println(cache.get("name1"));
    }
}
