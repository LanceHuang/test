package com.lance.test.cache;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 没有过期策略、常驻内存
 *
 * @author Lance
 */
public class RawCacheTest {

    private Map<String, Object> cache = new HashMap<>();

    @Test
    public void test() {
        cache.put("name", "Lance");
        System.out.println(cache.get("name"));
    }
}
