package com.lance.test.common.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class CollectionTest {

    @Test
    public void testForEach() {
        Collection<String> collection = Arrays.asList("name", "age", "heheda", "memeda");
        collection.forEach(System.out::println);
    }

    @Test
    public void testRemoveIf() {
        Collection<String> collection = new LinkedList<>();
        collection.add("name");
        collection.add("memeda");
        collection.add("lance");
        collection.forEach(System.out::println);

        collection.removeIf((v) -> (v.length() < 5));
        collection.forEach(System.out::println);
    }
}
