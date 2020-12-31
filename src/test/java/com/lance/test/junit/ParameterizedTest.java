package com.lance.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

/**
 * 参数化测试
 *
 * @author Lance
 * @since 2020/12/31
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    private String message;

    public ParameterizedTest(String message) {
        this.message = message;
    }

    @Parameterized.Parameters
    public static List<String> generateData() {
        List<String> data = new LinkedList<>();
        data.add("Lance1");
        data.add("Lance2");
        data.add("Lance3");
        return data;
    }

    @Test
    public void test() {
        System.out.println(message);
    }
}
