package com.lance.test.common.text;

import org.junit.Test;

import java.util.Date;
import java.util.Locale;

import java.text.DateFormat;

public class DataFormatTest {

    @Test
    public void test() {
        Locale zh = new Locale("zh", "CN");
        Locale en = new Locale("en", "US");
        Locale ja = new Locale("ja", "JA");
        Locale.setDefault(Locale.JAPAN);
        Date now = new Date();

        print(now, zh);
        System.out.println("---------------------------");
        print(now, en);
        System.out.println("---------------------------");
        print(now, ja);
    }

    // Formatted print
    public static void print(Date date, Locale locale) {
        DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, locale);
        DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                DateFormat.DEFAULT, locale);
        DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.SHORT, locale);
        System.out.println(locale + " FULL：" + df1.format(date));
        System.out.println(locale + " LONG：" + df2.format(date));
        System.out.println(locale + " DEFAULT：" + df3.format(date));
        System.out.println(locale + " SHORT：" + df4.format(date));
    }
}
