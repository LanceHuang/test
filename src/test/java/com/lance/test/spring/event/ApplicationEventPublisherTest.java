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

/**
 * spring自带的事件框架
 *
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationEventPublisherTest.class)
public class ApplicationEventPublisherTest {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster() {
        // 默认使用的也是SimpleApplicationEventMulticaster，但这里可以自定义一些配置
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setErrorHandler(new DefaultErrorHandler()); // 自定义异常处理器
//        multicaster.setTaskExecutor(); // 自定义任务执行器，可以让任务异步进行（变成异步事件）
        // 又或者自定义一个AbstractApplicationEventMulticaster，里面套两个SimpleApplicationEventMulticaster，其中一个TaskExecutor
        // 从而同时支持同步/异步事件
        return multicaster;
    }

    @EventListener
    public void testEvent(TestEvent event) { // 接收并处理事件，同步
        System.out.println("Receive TestEvent");
//        System.out.println(799/0); // 用于测试ErrorHandler
    }

    @Test
    public void test() {
        eventPublisher.publishEvent(new TestEvent()); // 发布事件
        System.out.println("Hello world");
    }
}
