package com.lance.test.common.lang;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
    private static AtomicInteger nextId = new AtomicInteger();

    /**
     * When you invoke get(), it'll invoke {@link ThreadLocal#get()} to get value.
     * If the thread hasn't set initial value, it'll invoke {@link ThreadLocal#initialValue()}.
     * So the following code will be fine.
     */
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }
}
