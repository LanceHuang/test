package com.lance.test.spring.component;

import com.lance.test.spring.component.service.IComponentScanTestService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2020/12/25
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ComponentScanTest.class)
@ComponentScan
public class ComponentScanTest {

    @Resource
    private IComponentScanTestService testService;

    @Test
    public void test() {
        Assert.assertNotNull(testService);
    }

}
