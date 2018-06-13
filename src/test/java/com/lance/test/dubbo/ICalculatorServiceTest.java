package com.lance.test.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:dubbo-demo-consumer.xml")
public class ICalculatorServiceTest {

    @Reference
    private ICalculatorService calculatorService;

    @Test
    public void execute() {
        System.out.println(calculatorService.execute(6, 102));
    }
}