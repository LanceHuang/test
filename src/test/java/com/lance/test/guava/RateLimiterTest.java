package com.lance.test.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

public class RateLimiterTest {

    @Test
    public void test() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }
}
