package com.lance.test.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

    @Test
    public void testJedis() {
        Jedis redisClient = new Jedis();
        String resultCode = redisClient.set("name", "Lance");
        System.out.println(resultCode);
        String name = redisClient.get("name");
        System.out.println(name);
        redisClient.close();
    }

    @Test
    public void test() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(50);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMaxWaitMillis(100 * 1000L);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        String resultCode = jedis.set("name", "Lance");
        System.out.println(resultCode);
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
        jedisPool.close();
    }
}
