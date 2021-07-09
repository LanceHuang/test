package com.lance.test.spring.event;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * spring自带的事件框架
 *
 * @author Lance
 */
@SpringBootTest(classes = ApplicationEventPublisherTest.Config.class)
public class ApplicationEventPublisherTest {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void test() {
        System.out.println("Publisher thread: " + Thread.currentThread());
        eventPublisher.publishEvent(new TestEvent());
    }

    @SpringBootApplication
    @EnableAsync
    public static class Config {

        @EventListener
        public void syncEvent1(TestEvent event) {
            System.out.println("syncEvent1 thread: " + Thread.currentThread());
        }

        @EventListener
        public void syncEvent2(TestEvent event) {
            System.out.println("syncEvent2 thread: " + Thread.currentThread());
        }

        @EventListener
        @Async
        public void asyncEvent(TestEvent event) {
            System.out.println("asyncEvent thread: " + Thread.currentThread());
        }

        @Bean
        public SimpleApplicationEventMulticaster applicationEventMulticaster() {
            // 默认使用的也是SimpleApplicationEventMulticaster，但这里可以
            // 自定义一些配置
            SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
            multicaster.setErrorHandler(new DefaultErrorHandler()); // 自定义异常处理器
//        multicaster.setTaskExecutor(); // 自定义任务执行器，可以让任务异步进行（变成异步事件）
            // 又或者自定义一个AbstractApplicationEventMulticaster，里面套两个SimpleApplicationEventMulticaster，其中一个TaskExecutor
            // 从而同时支持同步/异步事件
            return multicaster;
        }
    }
}
