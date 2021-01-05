package com.lance.test.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

/**
 * 参数化测试
 *
 * @author Lance
 * @since 2021/1/5
 */
public class ParameterTest {

    /**
     * 未提供测试数据，会报错
     */
    @ParameterizedTest
    public void test() {
        System.out.println("Hello junit5");
    }

    /**
     * 直接提供测试数据
     */
    @DisplayName("ValueSourceTest") // 测试用例名
    @ParameterizedTest(name = "run #{index} with [{arguments}]") // 单项测试名
    @ValueSource(strings = {"Lance", "Alice"})
    public void testValueSource(String value) {
        System.out.println(value);
    }

    /**
     * csv格式测试数据
     */
    @ParameterizedTest
    @CsvSource(value = {"Lance,100", "Alice, 90"})
    public void testCsvSource(String name, int value) {
        System.out.println(name + " " + value);
    }

    /**
     * 调用测试报告
     */
    @DisplayName("TestReporterTest")
    @ParameterizedTest
    @ValueSource(strings = {"Lance", "Alice"})
    public void testTestReporter(String value, TestReporter testReporter, TestInfo testInfo) {
        testReporter.publishEntry(testInfo.getDisplayName(), "Data: " + value);
    }

    /**
     * 枚举测试
     */
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    public void testEnumSource(TimeUnit timeUnit) {
        System.out.println(timeUnit);
    }

    /**
     * 空值测试
     */
    @ParameterizedTest
    @NullSource
    public void testNullSource(int num) { // 转型异常
        System.out.println(num);
    }

    /**
     * 空值测试
     */
    @ParameterizedTest
    @NullSource
    public void testNullSource(TimeUnit timeUnit) {
        System.out.println(timeUnit);
    }
}
