package com.lance.test.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance
 */
public class JUnit5Test {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach");
    }

    @Test
    public void test1() {
        System.out.println("Hello JUnit5");
    }

    @Test
    public void test2() {
        System.out.println("Hello JUnit5");
    }

    @Test
    public void testAssertAll() {
        int num = 5;
        assertAll(
                "num",
                () -> assertEquals(num, 5),
                () -> assertNotEquals(num, 6),
                this::test1,
                this::test2
        );
    }

    @Test
    public void testAssertThrows() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> {
            int num = 3 / 0;
        });
        System.out.println(e);

        ArithmeticException e2 = assertThrows(ArithmeticException.class, () -> {
            int num = 3 / 1;
        });
        System.out.println(e2);
    }

    @TestFactory
    public Stream<DynamicTest> testFactory() {
        List<Integer> data = Arrays.asList(656, 46, 1, 22, 1105, 5, 5, 2, 331);
        return DynamicTest.stream(
                data.listIterator(),
                item -> "Test: " + item,
                item -> testData(item)
        );
    }

    public void testData(int data) {
        System.out.println(data);
    }
}
