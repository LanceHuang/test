package com.lance.test.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LifecycleTest.class)
@Import({TestConfigurer.class, TestConfigurer2.class})
public class LifecycleTest {

    @Test
    public void test() {
        System.out.println("Hello world");
    }
}
