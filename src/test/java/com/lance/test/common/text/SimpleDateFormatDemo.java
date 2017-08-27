package com.lance.test.common.text;

import java.util.Date;

import java.text.SimpleDateFormat;

public class SimpleDateFormatDemo {



    public static void main(String[] args) {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // Date now = new Date();
        // System.out.println(sdf.format(now));
        Date now = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf1.format(now));
        SimpleDateFormat sdf2 = new SimpleDateFormat(
                "yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");
        System.out.println(sdf2.format(now));
        SimpleDateFormat sdf3 = new SimpleDateFormat(
                "现在是yyyy年 MM 月 dd 日，是今年的第 D 日");
        System.out.println(sdf3.format(now));

    }

}
