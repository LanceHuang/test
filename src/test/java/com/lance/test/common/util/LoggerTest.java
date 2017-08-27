package com.lance.test.common.util;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 各版本日志测试日志功能
 *
 * @author Lance
 * @date 2016年3月2日 下午2:53:55
 */
public class LoggerTest {

    /**
     * 测试javase自带Logger工具的功能
     */
    @Test
    public void testJDKLogger() {
        java.util.logging.Logger logger = java.util.logging.Logger
                .getLogger(this.getClass().getName());

        logger.setLevel(java.util.logging.Level.FINEST);

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");

        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * 测试开源代码org.apache.log4h.Logger的功能
     */
    @Test
    public void testLog4j() {
        Logger logger = Logger.getLogger(this.getClass());

        logger.debug("debug");
        logger.info("hi");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

        logger.error("msg cannot be null", new NullPointerException());
        logger.error("msg cannot be null", new NullPointerException());
    }

    /**
     * 测试slf4j的功能
     */
    @Test
    public void testSlf4j() {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this
                .getClass());

        logger.debug("呵呵哒");
        logger.info("么么哒");
    }
}
