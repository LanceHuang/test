package com.lance.test.common.util;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * @author Lance
 * @since 2017/2/24
 */
public class StringTokenizerTest {
    @Test
    public void test() {
        String text = "asd fgh j kl";
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }

}
