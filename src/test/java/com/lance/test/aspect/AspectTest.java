package com.lance.test.aspect;

import com.lance.test.aspect.config.Config;
import com.lance.test.aspect.service.IDocumentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class AspectTest {

    @Resource
    private IDocumentService documentService;

    @Test
    public void test() {
        System.out.println(documentService.getTitle(10));
    }
}
