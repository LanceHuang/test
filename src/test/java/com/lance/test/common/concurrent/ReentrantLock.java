package com.lance.test.common.concurrent;

import org.junit.Before;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Lance
 * @since 2018/11/16 11:33
 */
public class ReentrantLock implements Lock {

    private int    state = 0;
    private Thread owner = null;

    private Unsafe unsafe;
    private long   stateOffset;

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        unsafe = (Unsafe) field.get(null);
        stateOffset = unsafe.objectFieldOffset(ReentrantLock.class.getDeclaredField("state"));
    }

    @Override
    public void lock() {
        if (compareAndSetState(0, 1)) {
            owner = Thread.currentThread();
        } else if (owner == Thread.currentThread()) {
            return;
        } else {
            acquire();
        }
    }

    private void acquire() {
        //todo enqueue
        tryLock();
    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapObject(this, stateOffset, expect, update);
    }

    @Override
    public boolean tryLock() {
        Thread currentThread = Thread.currentThread();
//        if (owner == currentThread) {
//            state = state + 1;
//        } else {
//
//        }

        if (compareAndSetState(0, 1)) {
            owner = Thread.currentThread();
        }

        return false;
    }

    @Override
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (owner != currentThread) {
            throw new IllegalMonitorStateException();
        }

        int s = state - 1;
        state = s;
        if (s == 0) {
            owner = null;
        }
    }
}
