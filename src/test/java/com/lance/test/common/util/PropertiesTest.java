package com.lance.test.common.util;

import org.junit.Test;

import java.util.Properties;

/**
 * @author Lance
 * @date 2016/11/11 18:41
 */
public class PropertiesTest {

    @Test
    public void test() {
        Properties properties = new Properties();
        properties.setProperty("username", "lance");
        properties.setProperty("pwd", "111");
        System.out.println(properties);
    }
}
