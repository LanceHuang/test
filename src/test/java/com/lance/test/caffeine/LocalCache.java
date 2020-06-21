package com.lance.test.caffeine;

import java.util.HashMap;
import java.util.Map;

public class LocalCache<K, V> implements Cache<K, V> {

    private Map<K, V> data = new HashMap<>();

    @Override
    public void put(K key, V value) {
        this.data.put(key, value);
    }

    @Override
    public V get(K key) {
        return this.data.get(key);
    }
}
