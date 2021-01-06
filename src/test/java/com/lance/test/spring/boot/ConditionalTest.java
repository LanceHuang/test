package com.lance.test.spring.boot;

import com.lance.test.spring.boot.annotation.ConditionalOnFile;
import com.lance.test.spring.boot.service.ConditionOnClassTestService;
import com.lance.test.spring.boot.service.IConditionOnClassTestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2021/1/6
 */
@SpringBootTest(classes = ConditionalTest.class)
public class ConditionalTest {

    @Resource
    private IConditionOnClassTestService service;

    @Bean
    @ConditionalOnFile("log4j.properties")
    public IConditionOnClassTestService conditionOnClassTestService() {
        return new ConditionOnClassTestService();
    }

    @Test
    public void test() {
        Assertions.assertNotNull(service);
    }
}
