package com.lance.test.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
@PropertySource("classpath:/spring.properties")
public class SpringTest {

    @Resource
    private Environment env;

    @Value("${user.name}")
    private String username;

    @Test
    public void test() throws IOException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringTest.class);
//        ac.register(SpringTest.class);
//        ac.refresh();

        MyBean bean = ac.getBean(MyBean.class);
        System.out.println(bean);
        System.out.println(this);
        System.out.println(env);
        System.out.println(username);

//        System.in.read(); // Debug
    }

    @Bean
//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @Lazy
    public MyBean myBean() {
        System.out.println("Create MyBean");
        System.out.println(this);
        System.out.println();
        return new MyBean();
    }

    @Bean
    public MyBean2 myBean2() {
        System.out.println("Create MyBean2");
        System.out.println(myBean()); // Dead loop?
        System.out.println(this);
        System.out.println();
        return new MyBean2();
    }

}
