package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author Lance
 * @date 2018/3/7 9:39
 */
public class CountDownLatchTest {
    @Test
    public void testSync() {
        final CountDownLatch latch = new CountDownLatch(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
                latch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
                latch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
                latch.countDown();
            }
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

    @Test
    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Doing");
                }
            }
        }).start();

        System.out.println("Finished");
    }
}
