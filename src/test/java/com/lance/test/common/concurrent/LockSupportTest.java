package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Lance
 * @since 2018/11/15 21:46
 */
public class LockSupportTest {

    @Test
    public void test() {
        System.out.println("Ready...");

        final Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LockSupport.unpark(mainThread);
        }).start();


        LockSupport.park();
        System.out.println("Finished...");
    }

    @Test
    public void testParkNanos() {
        System.out.println("Ready...");

        final Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fi");

            LockSupport.unpark(mainThread);
        }).start();


        LockSupport.parkNanos(2000000000L);
        System.out.println("Finished...");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
