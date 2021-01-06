package com.lance.test.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.ParentContextApplicationContextInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Lance
 * @since 2020/12/30
 */
@SpringBootApplication
public class SpringApplicationTest {

    public static void main(String[] args) {
//        SpringApplication.run(SpringApplicationTest.class, args);

        SpringApplication springApplication = new SpringApplication(SpringApplicationTest.class);
//        springApplication.addInitializers(new ParentContextApplicationContextInitializer(parent)); // 父容器
        springApplication.setAdditionalProfiles("dev"); // --spring.profiles.active=dev
        springApplication.setBannerMode(Banner.Mode.OFF); // 启动时，控制台上显示的SpringBoot
        springApplication.setRegisterShutdownHook(false); // 不会在停服时，自动调用hoot

        ConfigurableApplicationContext ac = springApplication.run();
        SpringApplication.exit(ac); // 关闭容器，相当于shutdown hook的功能
    }
}
