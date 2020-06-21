package com.lance.test.caffeine;

public interface CacheWriter<K, V> {

    void write(K key, V value);
}
