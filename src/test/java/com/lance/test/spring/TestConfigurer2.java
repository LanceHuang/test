package com.lance.test.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Lance
 */
public class TestConfigurer2 {

    public TestConfigurer2() {
        System.out.println("TestConfigurer2 constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("TestConfigurer2 PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TestConfigurer2 PreDestroy");
    }
}
