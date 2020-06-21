package com.lance.test.caffeine;

public class CacheBuilder<K, V> {

    private CacheWriter<K, V> cacheWriter;
    private CacheLoader<K, V> cacheLoader;

    public CacheBuilder<K, V> writer(CacheWriter<K, V> cacheWriter) {
        this.cacheWriter = cacheWriter;
        return this;
    }

    public CacheBuilder<K, V> loader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = cacheLoader;
        return this;
    }

    public Cache<K, V> build() {
        if (this.cacheWriter == null || this.cacheLoader == null) {
            return new LocalCache<>();
        } else {
            LoadingCache<K, V> cache = new LoadingCache<>();
            cache.setCacheWriter(this.cacheWriter);
            cache.setCacheLoader(this.cacheLoader);
            return cache;
        }
    }
}
