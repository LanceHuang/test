package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author Lance
 * @since 2021/12/24
 */
public class SubjectTest {

    @Test
    public void test() throws InterruptedException {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        int testCount = 3;
        CountDownLatch latch = new CountDownLatch(testCount);
        for (int i = 0; i < testCount; i++) {
            new Thread(() -> {
                // 通过ThreadLocal创建多个Subject
                Subject subject = SecurityUtils.getSubject();
                System.out.println(subject);
                latch.countDown();
            }).start();
        }
        latch.await();
    }
}
