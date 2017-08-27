package com.lance.test.common.math;

import java.math.BigInteger;

public class BigDecimalDemo {

    public static void main(String[] args) {
//		BigDecimal bigDec1 = new BigDecimal("6");
//		BigDecimal bigDec2 = new BigDecimal("5");
        BigInteger bigInt1 = new BigInteger("6");
        BigInteger bigInt2 = new BigInteger("5");
//		int num = 8765543444533;

//		BigDecimal sum = bigDec1.divide(bigDec2);
//		BigDecimal sum = bigDec1.multiply(bigDec2);
        BigInteger sum = bigInt1.divide(bigInt2);

        System.out.println(sum.toString());
    }

}
