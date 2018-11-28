package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Lance
 * @since 2018/11/27 17:30
 */
public class LockTest {

    @Test
    public void testSynchronized() {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("t1 arrive");

            synchronized (lock) {
                System.out.println("t1 finished");
                lock.notifyAll();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 arrive");
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 finished"); //If thread-2 arrive in first, it may not worked
            }
        });


        t1.start();
        t2.start();

        sleep(5);
    }

    @Test
    public void testLockSupport() {


        Thread t1 = new Thread(() -> {
            System.out.println("t1 arrive");
            LockSupport.park(this);
            System.out.println("t1 finished"); //If thread-2 arrive in first, it also will work
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 arrive");
            LockSupport.unpark(t1);
            System.out.println("t2 finished");
        });

        t2.start();
        t1.start();

        sleep(5);
    }


    private void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
