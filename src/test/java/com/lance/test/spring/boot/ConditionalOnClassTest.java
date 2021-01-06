package com.lance.test.spring.boot;

import com.lance.test.spring.boot.service.ConditionOnClassTestService;
import com.lance.test.spring.boot.service.IConditionOnClassTestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2021/1/6
 */
@SpringBootTest(classes = ConditionalOnClassTest.class)
public class ConditionalOnClassTest {

    @Resource
    private IConditionOnClassTestService service;

    /**
     * ConditionTestService被加载后，才可以调用该方法
     */
    @Bean
    @ConditionalOnClass(name = "com.lance.test.spring.boot.service.ConditionTestService")
    public IConditionOnClassTestService conditionOnClassTestService() {
        return new ConditionOnClassTestService();
    }

    @Test
    public void test() {
        Assertions.assertNotNull(service);
    }
}
