package com.lance.test.log;

import org.junit.Test;

public class LoggerTest {

    @Test
    public void testDebug() {
        LoggerUtil.debug("Hello LoggerUtil");
        LoggerUtil.debug("Hello {}", "Lance");
        LoggerUtil.debug("Hello Exception", new IllegalArgumentException("hi"));
    }

    @Test
    public void testInfo() {
        LoggerUtil.info("Hello LoggerUtil");
        LoggerUtil.info("Hello {}", "Lance");
        LoggerUtil.info("Hello Exception", new IllegalArgumentException("hi"));
    }

    @Test
    public void testError() {
        LoggerUtil.error("Hello LoggerUtil");
        LoggerUtil.error("Hello {}", "Lance");
        LoggerUtil.error("Hello Exception", new IllegalArgumentException("hi"));
    }
}
