package com.lance.test.common.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    @Test
    public void test() throws InterruptedException {
        new SimpleAsyncExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello ExecutorService");
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }
}
