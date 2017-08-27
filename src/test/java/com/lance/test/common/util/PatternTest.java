package com.lance.test.common.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lance
 * @date 2016/10/8
 */
public class PatternTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        while (scan.hasNext()) {
            String str = scan.nextLine();

            Matcher matcher = pattern.matcher(str);

            int wordsCount = 0;
            while (matcher.find()) wordsCount++;
            System.out.println(str + " 单词数：" + wordsCount);
        }
    }
}
