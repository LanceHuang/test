package com.lance.test.mvel;

import org.junit.Test;
import org.mvel2.MVEL;

/**
 * @author Lance
 * @since 2019/7/2 11:49
 */
public class MVELTest {

    @Test
    public void test() {
        String expression = "getValue+getValue()+max(8,10)";
        Integer result = MVEL.eval(expression, this, Integer.class);
        System.out.println(result);
    }

    public int getValue() {
        return 56;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }
}
