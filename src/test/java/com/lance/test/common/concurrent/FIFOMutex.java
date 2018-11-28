package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Non reentrant lock
 *
 * @author Lance
 * @see java.util.concurrent.locks.LockSupport comment
 * @since 2018/11/16 10:30
 */
public class FIFOMutex {

    private final AtomicBoolean locked  = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        // Block while not first in queue or cannot acquire lock
        while (waiters.peek() != current ||
                !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted()) { // ignore interrupts while waiting
                wasInterrupted = true;
            }
        }

        waiters.remove();
        if (wasInterrupted) {         // reassert interrupt status on exit
            current.interrupt();
        }
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    @Test
    public void test() {
        final FIFOMutex mutex = new FIFOMutex();

        new Thread(() -> {
            System.out.println("A Read to lock");

            mutex.lock();
            System.out.println("A lock once");
            mutex.lock(); //Dead lock
            System.out.println("A lock twice");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mutex.unlock();
            mutex.unlock();
            System.out.println("A unlock");
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("B Read to lock");

            mutex.lock();
            System.out.println("B lock once");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mutex.unlock();
            System.out.println("B unlock");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}