package com.lance.test.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lance
 * @since 2017/4/13
 */
public final class LogUtils {

    private static Logger LOG = LoggerFactory.getLogger(LogUtils.class);

    private LogUtils() {

    }

    public static void info(String msg, Throwable t) {
        LOG.info(msg, t);
    }
}
