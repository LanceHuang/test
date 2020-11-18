package com.lance.test.common.lang;

import org.junit.Test;

public class ThreadLocalTest {

    private static final ThreadLocal<Thread> currentThreadName = ThreadLocal.withInitial(Thread::currentThread);

    @Test
    public void test() throws InterruptedException {
        System.out.println(currentThreadName.get()); // Thread[main,5,main]
        new Thread(() -> System.out.println(currentThreadName.get())).start(); // Thread[Thread-0,5,main]

        Thread.sleep(3000L);
    }
}
