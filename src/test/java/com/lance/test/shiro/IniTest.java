package com.lance.test.shiro;

import org.apache.shiro.config.Ini;
import org.junit.Test;

import java.util.Map;

public class IniTest {

    @Test
    public void test() {
        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");

        for (Ini.Section section : ini.getSections()) {
            System.out.println("Section: " + section.getName());
            for (Map.Entry<String, String> entry : section.entrySet()) {
                System.out.println("\t Value: " + entry.getKey() + "=" + entry.getValue());
            }
        }
    }
}
