package com.lance.test.common.lang;

import org.junit.Test;

public class ThreadLocalTest {
    @Test
    public void test() {
        System.out.println(ThreadId.get());
        System.out.println(ThreadId.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Id: " + ThreadId.get());
            }
        }).start();
    }
}
