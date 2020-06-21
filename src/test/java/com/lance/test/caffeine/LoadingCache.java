package com.lance.test.caffeine;

import java.util.HashMap;
import java.util.Map;

public class LoadingCache<K, V> implements Cache<K, V> {

    private Map<K, V> data = new HashMap<>();

    private CacheWriter<K, V> cacheWriter;
    private CacheLoader<K, V> cacheLoader;

    @Override
    public void put(K key, V value) {
        this.data.put(key, value);

        if (this.cacheWriter != null) {
            this.cacheWriter.write(key, value);
        }
    }

    @Override
    public V get(K key) {
        V value = this.data.get(key);
        if (value != null) {
            return value;
        }

        if (this.cacheLoader != null) {
            return this.cacheLoader.load(key);
        }
        return null;
    }

    public void setCacheWriter(CacheWriter<K, V> cacheWriter) {
        this.cacheWriter = cacheWriter;
    }

    public void setCacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = cacheLoader;
    }
}