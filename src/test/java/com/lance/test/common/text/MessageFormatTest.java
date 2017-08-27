package com.lance.test.common.text;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * @author Lance
 * @since 2017/4/9
 */
public class MessageFormatTest {

    @Test
    public void test() {
        String text = "Hello {0} {1}!";
        String username = "Lance";
        String password = "123456";
        System.out.println(MessageFormat.format(text, username, password));
    }
}
