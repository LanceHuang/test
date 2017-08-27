package com.lance.test.common.lang;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void testSetDaemon() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("测试:" + i);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println(thread.getName());

        System.err.println("main");

    }

}
