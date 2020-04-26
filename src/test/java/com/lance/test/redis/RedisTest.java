package com.lance.test.redis;

import com.lance.test.log.LoggerUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {
    private Jedis redisClient;

    @Before
    public void before() {
        redisClient = new Jedis();
        LoggerUtil.info("Before");
    }

    @After
    public void after() {
        redisClient.close();
        System.out.println("After");
    }

    @Test
    public void testSet() {
        String resultCode = redisClient.set("name", "Lance");
        System.out.println(resultCode);
    }

    @Test
    public void testGet() {
        String name = redisClient.get("name");
        System.out.println(name);
    }
}
