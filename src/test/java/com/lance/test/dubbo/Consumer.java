package com.lance.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-demo-consumer.xml");
        IEchoService echoService = ac.getBean(IEchoService.class);
        System.out.println("Return: " + echoService.echo("Hello dubbo"));
    }
}
