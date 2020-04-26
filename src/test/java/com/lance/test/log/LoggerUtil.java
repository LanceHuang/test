package com.lance.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility of log
 *
 * @author Lance
 */
public class LoggerUtil {

    // TODO: 2020/2/21 %l 
    private static final Logger log = LoggerFactory.getLogger(LoggerUtil.class);

    public static void debug(String message, Object... args) {
        log.debug(message, args);
    }

    public static void debug(String message, Throwable e) {
        log.debug(message, e);
    }

    public static void info(String message, Object... args) {
        log.info(message, args);
    }

    public static void info(String message, Throwable e) {
        log.info(message, e);
    }

    public static void error(String message, Object... args) {
        log.error(message, args);
    }

    public static void error(String message, Throwable e) {
        log.error(message, e);
    }
}
