package com.lance.test.common.concurrent;

import org.junit.Test;

public class ExecutorTest {

    @Test
    public void test() {
        new SimpleSyncExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Executor");
            }
        });
    }
}
