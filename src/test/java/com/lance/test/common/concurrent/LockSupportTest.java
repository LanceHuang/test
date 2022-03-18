package com.lance.test.common.concurrent;

import com.lance.common.tool.ThreadUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 类似于Object.wait，但不需要放在synchronized中
 *
 * @author Lance
 * @since 2018/11/15 21:46
 */
public class LockSupportTest {

    @Test
    public void test() {
        final Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            // 子线程休眠1s
            ThreadUtils.sleep(1000L);
            // 子线程执行
            System.out.println("子线程执行");

            // 唤醒主线程
            System.out.println("唤醒主线程");
            LockSupport.unpark(mainThread);
        }).start();

        // 挂起主线程
        System.out.println("挂起主线程");
//        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500L));
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1500L));

        // 主线程唤醒后执行
        System.out.println("主线程执行");
    }
}
