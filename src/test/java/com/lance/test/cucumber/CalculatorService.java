package com.lance.test.cucumber;

/**
 * @author Lance
 * @since 2021/1/5
 */
public class CalculatorService implements ICalculatorService {

    @Override
    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public int max(int num1, int num2) {
        return Math.max(num1, num2);
    }
}
