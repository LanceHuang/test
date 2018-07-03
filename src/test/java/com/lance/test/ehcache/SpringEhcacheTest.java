package com.lance.test.ehcache;

import com.lance.test.ehcache.service.IMsgService;
import com.lance.test.ehcache.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EhcacheConfig.class)
public class SpringEhcacheTest {

    @Resource
    private IMsgService msgService;

    @Resource
    private IUserService userService;

    @Test
    public void test() throws InterruptedException {
        System.out.println(msgService.greet("Lance"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(msgService.greet("Alice"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(msgService.greet("Lance"));

        System.out.println(userService.getUserById(1));
        System.out.println(userService.getUserById(10));
        System.out.println(userService.getUserById(1));
    }

}
