package com.lance.test.spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.Executors;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MixApplicationEventMulticasterTest.class)
public class MixApplicationEventMulticasterTest {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Bean
    public MixApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster syncMulticaster = new SimpleApplicationEventMulticaster();
        SimpleApplicationEventMulticaster asyncMulticaster = new SimpleApplicationEventMulticaster();
        asyncMulticaster.setTaskExecutor(Executors.newSingleThreadExecutor());
        return new MixApplicationEventMulticaster(syncMulticaster, asyncMulticaster);
    }

    @EventListener
    public void testEvent(TestEvent event) { // 同步
        System.out.println("Receive TestEvent");
        System.out.println("WorkThread: " + Thread.currentThread());
    }

    @EventListener
    public void testAsyncEvent(TestAsyncEvent event) { // 异步
        System.out.println("Receive TestAsyncEvent");
        System.out.println("WorkThread: " + Thread.currentThread());
    }


    @Test
    public void test() throws InterruptedException {
        eventPublisher.publishEvent(new TestEvent()); // 发布事件
        eventPublisher.publishEvent(new TestAsyncEvent(this));
        System.out.println("Hello world");
        System.out.println("MainThread: " + Thread.currentThread());

        Thread.sleep(1000L); // 等待异步任务运行完成
    }
}
