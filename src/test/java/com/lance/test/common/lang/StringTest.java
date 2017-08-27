package com.lance.test.common.lang;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

import static com.lance.common.util.ConsolePrinter.*;
import static com.lance.common.util.Timer.*;

public class StringTest {

    @Test
    public void testMatches() {
        String[] urls = {
                "https://www.oschina.net/blog",
                "https://www.oschina.net/blog?classification=428602",
                "https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=428602&p=2",
                "https://www.oschina.net/action/ajax/get_more_daily_blog?classification=428602&p=2",
                "https://www.oschina.net/action/ajax/get_more_recent_blog?classification=428602&p=2",
                "https://my.oschina.net/dolphinboy/blog/857206",
                "https://www.oschina.net/question?catalog=1",
                "https://www.oschina.net/question?catalog=1&show=updated",
                "https://www.oschina.net/question/_widgets/_list_content?catalog=1&show=updated&p=2"};
        String[] patterns = {"https://www\\.oschina\\.net/blog(\\?classification=\\d+)?",
                "https://www\\.oschina\\.net/action/ajax/\\w+\\?classification=\\d+&p=\\d+",
                "https://my\\.oschina\\.net/(\\d|\\w)+/blog/\\d+",
                "https://www\\.oschina\\.net/question\\?catalog=\\d+",
                "https://www\\.oschina\\.net/question\\?catalog=\\d+&show=\\w+",
                "https://www\\.oschina\\.net/question/_widgets/_list_content\\?catalog=\\d+&show=\\w+&p=\\d+"};

        for (String url : urls) {
            for (String pattern : patterns) {
                if (url.matches(pattern)) {
                    println(url + " " + pattern);
                }
            }
            println("------------------------------------");
        }

    }

    @Test
    public void testGetBytes() throws UnsupportedEncodingException {
        System.out.println("-------------Length of byte array--------------");
        System.out.println("\"hi\".getBytes():" + "hi".getBytes().length);
        System.out.println("\"你\".getBytes():" + "你".getBytes().length);
        System.out.println("\"你\".getBytes(\"utf-8\"):" + "你".getBytes("utf-8").length);
        System.out.println("\"你\".getBytes(\"gbk\"):" + "你".getBytes("gbk").length);
        System.out.println("\"你\".getBytes(\"gb2312\"):" + "你".getBytes("gb2312").length);
        System.out.println("\"你\".getBytes(\"gb18030\"):" + "你".getBytes("gb18030").length);

        System.out.println("------------Content of byte array---------------");
        System.out.println(Arrays.toString("你".getBytes()));
        System.out.println(Arrays.toString("你".getBytes("utf-8")));
        System.out.println(Arrays.toString("你".getBytes("gb2312")));
        System.out.println(Arrays.toString("你".getBytes("gbk")));
        System.out.println(Arrays.toString("你".getBytes("gb18030")));

        System.out.println("------------Content of byte array mixing english and chinese---------------");
        System.out.println(Arrays.toString("你i".getBytes()));
        System.out.println(Arrays.toString("你i".getBytes("utf-8")));
        System.out.println(Arrays.toString("你i".getBytes("gb2312")));
        System.out.println(Arrays.toString("你i".getBytes("gbk")));
        System.out.println(Arrays.toString("你i".getBytes("gb18030")));
    }

    @Test
    public void testIntern() {
        String str1 = new StringBuilder("he").append("llo").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    @Test
    public void testMatchesString() {
        String text = "p2Pasa";
        System.out.println(text.matches(".*p2p.*"));
        System.out.println(text.matches(".*p2P.*"));
        System.out.println(text.matches(".*P2p.*"));
        System.out.println(text.matches(".*P2P.*"));
        System.out.println(text.matches(".*(p|P)2(p|P).*"));
    }

    /**
     * Test for immutable class
     */
    public void testImmutableClass() {
        String str = new String("abcdae");
        System.out.println(str.replace('a', 'A'));
        System.out.println(str);
    }

    /**
     * Test for mutable class
     */
    public void testMutableClass() {
        // StringBuffer buffer = new StringBuffer("heheda");
        StringBuilder builder = new StringBuilder("memeda");
        builder.replace(0, 2, "asd");
        System.out.println(builder.toString());
    }

    /**
     * Test for mutable class's replace()
     */
    public void testMutableClassReplace() {
        String str = new String("abcdef");
        char[] chs = new char[10];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = 'Z';
        }
        System.out.println(Arrays.toString(chs));
        str.getChars(1, 3, chs, 5);
        System.out.println(Arrays.toString(chs));
    }

    /**
     * Test for StringBuilder.equals()
     */
    public void testStringBuilderEquals() {
        StringBuilder s1 = new StringBuilder("Test");
        StringBuilder s2 = new StringBuilder("Test");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2)); // Line 1
        System.out.println(s1.toString() == s2.toString()); // Line 2
        if (s1.toString() == s2.toString())
            System.out.println("True"); // Line 3
    }

    /**
     * Test for impove the efficency of StringBuilder.equals();
     */
    public void testImproveEquals() {

        StringBuilder s1 = new StringBuilder("asdasasasdasda");
        StringBuilder s2 = new StringBuilder("asdasasasdasda");
        int times = 100000000;

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            s1.toString().equals(s2.toString());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("耗时：" + (t2 - t1));

        t1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            if (s1.length() == s2.length()) {
                int len = s1.length();
                while (--len >= 0) {
                    if (s1.charAt(len) == s2.charAt(len))
                        ;
                }
            }
        }
        t2 = System.currentTimeMillis();
        System.out.println("耗时：" + (t2 - t1));

    }

    public void testPrintAndPrintf() {
        int times = 100000000;
        // int times = 100;

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            // System.out.print("Happy new Year!"
            // + "Lance");
            System.out.println("Happy new Year!" + "Lance");
        }
        long t2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("耗时：" + (t2 - t1));
        System.out.println();

        t1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            System.out.printf("Happy new Year!", "Lance");
        }
        t2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("耗时：" + (t2 - t1));
        System.out.println();

    }

    public void testStringAddStringNull() {
        String s1 = null;
        s1 += "h";
        System.out.println(s1);
    }

    public void testStringBuilderAppendStringNull() {
        StringBuilder s1 = new StringBuilder("a");
        String ss = null;
        s1.append(ss);
        System.out.println(s1.toString());

    }

    public void testReplaceAll() {
        String text = "2015年212月12人";
        System.out.println(text.replaceAll("(年|月|日)", "-"));

        String str = "2016-03-18  11:2:00";
        System.out.println(str.replaceAll("[ ]+", " "));
    }

}
