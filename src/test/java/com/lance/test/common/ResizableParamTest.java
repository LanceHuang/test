package com.lance.test.common;

/**
 * Test for resizable param
 *
 * @author Lance
 * @date 2016/9/10
 */
public class ResizableParamTest {

    /**
     * Calculate price
     */
    public void calc(double price, double discount) {
        double result = price * discount;
        System.out.println("Single discount, result=" + result);
    }

    /**
     * Calculate price
     */
    public void calc(double price, double... discounts) {
        double result = price;
        for (double discount : discounts) {
            result *= discount;
        }
        System.out.println("Multiple discount, result=" + result);
    }

    public static void main(String[] args) {
        ResizableParamTest test = new ResizableParamTest();
        test.calc(256.0, 0.75);
    }

}
