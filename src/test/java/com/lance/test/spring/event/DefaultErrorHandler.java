package com.lance.test.spring.event;

import org.springframework.util.ErrorHandler;

/**
 * 默认异常处理器
 *
 * @author Lance
 */
public class DefaultErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        // 异常处理
        System.out.println("报错啦");
        t.printStackTrace();
    }
}
