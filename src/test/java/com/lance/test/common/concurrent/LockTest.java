package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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

    @Test
    public void testLockInterruptibly() {
        Lock lock = new ReentrantLock();

        /*
            Thread 2 arrive
            Thread 1 arrive
            Thread 2 do sth
         */
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 arrive");
//                lock.lock();
                lock.lockInterruptibly();
                System.out.println("Thread 1 do sth");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread 2 arrive");
                lock.lock();
                sleep(TimeUnit.MILLISECONDS, 15);
                t1.interrupt();
                System.out.println("Thread 2 do sth");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t2.start();
        sleep(TimeUnit.MILLISECONDS, 10);
        t1.start();

        sleep(TimeUnit.SECONDS, 3);
    }


    private void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep(TimeUnit timeUnit, long t) {
        try {
            timeUnit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
