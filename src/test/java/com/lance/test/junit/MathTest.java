package com.lance.test.junit;

public class MathTest {

    private String msg;

    @Before
    public void before() {
        msg = "Hello junit";
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void test() {
        System.out.println("Test message: " + msg);
    }
}
