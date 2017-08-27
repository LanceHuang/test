package com.lance.test.common.lang;

import org.junit.Test;

/**
 * @author Lance
 * @date 2016/10/7
 */
public class MathTest {

    @Test
    public void testRound(){
        System.out.println(0x1.0p-2);
        System.out.println(0x10);
        System.out.println(0x1p-2);
        System.out.println(0x11p-2);
        System.out.println(0x11P-2);
        System.out.println(0x1ep-3);
        System.out.println(010);
    }

    @Test
    public void testLog() {
        System.out.println(Math.log(1.11));
        System.out.println(Math.log(2));
        System.out.println(Math.log(2.7));
        System.out.println(Math.log(Math.E));
        System.out.println(Math.log(10));

        System.out.println(Math.log(100)/Math.log(10));

        System.out.println(Math.log(1.11)/Math.log(1.11));
        System.out.println(Math.log(1)/Math.log(1.11));
        System.out.println(Math.log(0)/Math.log(1.11));
    }

}
