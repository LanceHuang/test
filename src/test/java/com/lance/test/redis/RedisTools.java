package com.lance.test.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author Lance
 * @date 2018-7-3 14:38:55
 */
public final class RedisTools {

    private static Jedis redisClient;

    private RedisTools() {
        throw new IllegalStateException();
    }

    private static final String SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";

    public static enum ExpireTimeType {
        EX("EX"), PX("PX");

        private String type;

        ExpireTimeType(String type) {
            this.type = type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * Try to get lock, and set expire seconds.
     *
     * @param lockKey    redis key
     * @param lockVal    redis value
     * @param expireTime seconds
     * @return {@code true} if successfully get lock
     */
    public static boolean tryLock(String lockKey, String lockVal, int expireTime) {
        return tryLock(lockKey, lockVal, ExpireTimeType.EX, expireTime);
    }

    /**
     * Try to get lock, and set expire time.
     *
     * @param lockKey    redis key
     * @param lockVal    redis value
     * @param expx       expire type, EX or PX
     * @param expireTime Seconds if use {@code EX}
     * @return {@code true} if successfully get lock
     */
    public static boolean tryLock(String lockKey, String lockVal, ExpireTimeType expx, long expireTime) {
        if (isEmpty(lockKey) || isEmpty(lockVal) || null == expx || expireTime <= 0) {
            throw new IllegalArgumentException();
        }

        String result = redisClient.set(lockKey, lockKey, SET_IF_NOT_EXIST, expx.type, expireTime);
        return SUCCESS.equals(result);
    }

    private static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }


    /**
     * Try to release lock only if {@code lockKey} matches redis key and {@code lockVal} matches redis value.
     *
     * @param lockKey redis key
     * @param lockVal redis value
     * @return {@code true} if successfully release lock
     */
    public static boolean tryUnlock(String lockKey, String lockVal) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = redisClient.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockVal));

        return SUCCESS.equals(result);
    }

}
