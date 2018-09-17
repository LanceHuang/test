package com.lance.test.netty;

import io.netty.util.internal.TypeParameterMatcher;
import org.junit.Test;

public class TypeParameterMatcherTest {

    @Test
    public void test() {
        StringData data = new StringData();
        TypeParameterMatcher m1 = TypeParameterMatcher.find(data, StringData.class, "D");
        System.out.println(m1.getClass());
    }
}


class Data<D> {
    private D data;

    public Data() {
    }

    public Data(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}

class StringData extends Data<String> {

}


class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
