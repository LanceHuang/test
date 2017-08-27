package com.lance.test.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author Lance
 * @since 2017/3/6
 */
public class RedisTest {

    private Jedis redis = null;
    private String redisHostname = "192.168.147.99";
    private int redisPort = 6379;

    @Before
    public void before() {
        redis = new Jedis(redisHostname, redisPort);
    }

    @Test
    public void testKeys() {
        Set<String> keys = redis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void testSet() {
        redis.set("pwd", "123");
    }

    @Test
    public void testDel() {
        System.out.println(redis.del("set_www.oschina.net"));
    }

    @Test
    public void testGet() {
        System.out.println(redis.get("pwd"));
    }

    @After
    public void after() {
        redis.close();
    }

}
