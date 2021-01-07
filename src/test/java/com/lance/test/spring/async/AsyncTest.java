package com.lance.test.spring.async;

import org.junit.jupiter.api.Test;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Lance
 * @since 2021/1/7
 */
@SpringBootTest(classes = AsyncTest.Config.class)
public class AsyncTest {

    @Autowired
    private AsyncBean asyncBean;

    @Test
    public void test() throws InterruptedException {
        System.out.println(Thread.currentThread()); // thread1
        asyncBean.syncMethod(); // thread1
        asyncBean.asyncMethod(); // thread2

        Thread.sleep(3000L);
    }

    @SpringBootApplication
    @EnableAsync
    public static class Config extends AsyncConfigurerSupport {

        @Bean
        public AsyncBean asyncBean() {
            return new AsyncBean();
        }

        /**
         * 自定义线程池
         */
        @Override
        public Executor getAsyncExecutor() {
            return Executors.newSingleThreadExecutor();
        }

        /**
         * 处理异常
         */
        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            return new MyAsyncUncaughtExceptionHandler();
        }
    }

    public static class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            // 打印异常，或者做一些特殊处理
            ex.printStackTrace();
        }
    }
}
