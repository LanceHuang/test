package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lance
 * @see java.util.concurrent.locks.Condition
 * @since 2018/11/28 16:44
 */
public class ConditionTest {

    @Test
    public void test() {

        SharedResource sharedResource = new SharedResource(5);
//        LinkedBlockingQueue<Integer> sharedResource = new LinkedBlockingQueue<>(5);

        new Thread(() -> {
            for (int i = 0; i < 14; i++) {
                sleep(100);
                System.out.println("生产：" + i);
                sharedResource.push(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sleep(150);
                System.out.println("消费：" + sharedResource.pop());
            }
        }).start();

        sleep(10000);
    }

    private void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


/**
 * @see java.util.concurrent.ArrayBlockingQueue
 */
class SharedResource {
    private int      capacity;
    private Object[] items;
    private int      size;

    private Lock      lock;
    private Condition notEmpty;
    private Condition notFull;

    public SharedResource(int capacity) {
        this.capacity = capacity;
        this.items = new Object[this.capacity];

        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }


    public void push(Object obj) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            items[size++] = obj;
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object pop() {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }

            Object x = items[--size];
            notFull.signal();
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}