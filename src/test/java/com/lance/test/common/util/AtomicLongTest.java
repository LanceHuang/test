package com.lance.test.common.util;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Lance
 * @date 2016/10/30
 */
public class AtomicLongTest {

    @Test
    public void test() {
        AtomicLong aLong = new AtomicLong();
        System.out.println(aLong.get());
        System.out.println(aLong.incrementAndGet());
        System.out.println(aLong.get());
    }
}
