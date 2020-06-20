package com.lance.test.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    public void test() {
        // 1. 读数据时，有数据，则直接返回；没有数据，则调用CacheLoader.load创建，并且缓存结果
        // 2. 写数据时，先写到缓存，再调用CacheWriter.write
        // invoker -> cache -> db

        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("name", "Lance");

        LoadingCache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterAccess(10L, TimeUnit.SECONDS)
                .expireAfterWrite(10L, TimeUnit.SECONDS)
                .writer(new CacheWriter<String, Object>() {
                    @Override
                    public void write(@NonNull String s, @NonNull Object o) {
                        data.putIfAbsent(s, o);
                    }

                    @Override
                    public void delete(@NonNull String s, @Nullable Object o, @NonNull RemovalCause removalCause) {
                        System.out.println("delete");
                    }
                })
                .build(new CacheLoader<String, Object>() {
                    @Nullable
                    @Override
                    public Object load(@NonNull String s) throws Exception {
                        return data.get(s);
                    }
                });

        System.out.println(data);
        System.out.println(cache.get("name"));
        System.out.println(cache.get("name"));

        cache.put("age", 12);
        System.out.println(cache.get("age"));
    }
}
