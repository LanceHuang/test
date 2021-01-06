package com.lance.test.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Lance
 * @since 2021/1/6
 */
@SpringBootApplication
public class SpringApplicationBuilderTest {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringApplicationBuilderTest.class)
//                .parent(ac)
                .profiles("dev")
                .bannerMode(Banner.Mode.OFF)
                .registerShutdownHook(true)
                .run();
    }
}
