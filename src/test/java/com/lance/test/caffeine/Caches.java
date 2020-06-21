package com.lance.test.caffeine;

public class Caches {

    public static <K, V> CacheBuilder<K, V> newBuilder() {
        return new CacheBuilder<>();
    }
}
