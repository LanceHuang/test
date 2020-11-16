package com.lance.test.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存
 *
 * @author Lance
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {

    private int maxSize;

    public LruCache(int maxSize) {
        super(maxSize, 0.75F, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > this.maxSize;
    }
}
