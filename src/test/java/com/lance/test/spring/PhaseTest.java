package com.lance.test.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * phase值小的，start先运行，stop后运行
 *
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PhaseTest.class)
public class PhaseTest {

    @Bean
    public PhaseBean1 phaseBean1() {
        return new PhaseBean1();
    }

    @Bean
    public PhaseBean2 phaseBean2() {
        return new PhaseBean2();
    }

    @Test
    public void test() {
        System.out.println("Hello world");
    }
}
