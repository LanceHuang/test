package com.lance.test.spring.async;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Lance
 * @since 2021/1/7
 */
public class AsyncBean {

    public void syncMethod() {
        System.out.println(Thread.currentThread());
    }

    @Async
    public void asyncMethod() {
        System.out.println(Thread.currentThread());
    }
}
