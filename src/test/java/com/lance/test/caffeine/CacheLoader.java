package com.lance.test.caffeine;

public interface CacheLoader<K, V> {

    V load(K key);
}
