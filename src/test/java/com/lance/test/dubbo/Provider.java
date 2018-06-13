package com.lance.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dubbo-demo-provider.xml");
        ac.start();

        //Press any key to continue
        System.in.read();
    }
}
