package com.lance.test.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapTest {

    @Test
    public void testGetOrDefault() {
        Map<String, String> map = Collections.singletonMap("name", "lance");
        Assert.assertEquals(map.get("name"), "lance");
        Assert.assertNull(map.get("age"));

        Assert.assertEquals(map.getOrDefault("name", null), "lance");
        Assert.assertEquals(map.getOrDefault("age", "24"), "24");
        Assert.assertNotEquals(map.getOrDefault("age", "24"), "23");
    }

    @Test
    public void testForEach() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        map.put("age", "24");

        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void testReplaceAll() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        map.put("age", "24");

        map.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println();

        map.replaceAll((k, v) -> "data-" + v);
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void testRemove() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        map.put("age", "24");
        Assert.assertEquals(map.size(), 2);

        map.remove("name", "alice");
        Assert.assertEquals(map.size(), 2);

        map.remove("name");
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    public void testReplace() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        Assert.assertEquals(map.get("name"), "lance");

        map.replace("name", "jack", "alice");
        Assert.assertEquals(map.get("name"), "lance");

        map.replace("name", "lance", "alice");
        Assert.assertEquals(map.get("name"), "alice");
    }

    @Test
    public void testMerge() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        map.put("age", "24");
        System.out.println(map);

        map.merge("name", "data:", (v1, v2) -> v1 + v2);
        System.out.println(map);
    }

    @Test
    public void testCompute() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "lance");
        map.put("age", "24");
        System.out.println(map);

        map.compute("name", (k, v) -> k);
        System.out.println(map);
    }

    @Test
    public void testComputeIfAbsent() {
        Map<String, Object> map = new HashMap<>();

        map.computeIfAbsent("name", this::compute);
        System.out.println(map);

        map.computeIfAbsent("name", this::compute);
        System.out.println(map);
    }

    private Object compute(String key) {
        System.out.println("Compute data...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Data: " + key;
    }
}

