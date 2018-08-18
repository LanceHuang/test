package com.lance.test.ehcache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EhcacheTest {

//    private CacheManager cacheManager;
//    private String cacheAlia = "cache1";
//
//    @Before
//    public void before() {
//        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
//        cacheManager.createCache(
//                cacheAlia,
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                        Long.class,
//                        String.class,
//                        ResourcePoolsBuilder.heap(10)));
//    }
//
//    @After
//    public void after() {
//        cacheManager.close();
//    }
//
//    @Test
//    public void test() {
//        Cache<Long, String> cache = cacheManager.getCache(cacheAlia, Long.class, String.class);
//        String cacheValue = "Hello ehcache";
//        cache.put(99L, cacheValue);
//
//        Assert.assertSame(cacheValue, cache.get(99L));
//        Assert.assertNotSame(cacheValue, cache.get(1L));
//    }
}
