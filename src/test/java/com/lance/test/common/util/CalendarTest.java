package com.lance.test.common.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Lance
 * @since 2017/3/12
 */
public class CalendarTest {
    @Test
    public void test() {
        System.out.println(formatDate("16小时"));
        System.out.println(formatDate("1天"));
        System.out.println(formatDate("1周"));
        System.out.println(formatDate("3个月"));
        System.out.println(formatDate("2年"));
    }

    private String formatDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar pubDate = Calendar.getInstance();

        if (str.contains("分钟")) {
            int minutes = Integer.parseInt(str.substring(0, str.indexOf("分钟")));
            pubDate.add(Calendar.MINUTE, -minutes);
        } else if (str.contains("时")) {
            int hours = Integer.parseInt(str.substring(0, str.indexOf("小时")));
            pubDate.add(Calendar.HOUR, -hours);
        } else if (str.contains("天")) {
            int days = Integer.parseInt(str.substring(0, str.indexOf("天")));
            pubDate.add(Calendar.DAY_OF_MONTH, -days);
        } else if (str.contains("周")) {
            int weeks = Integer.parseInt(str.substring(0, str.indexOf("周")));
            pubDate.add(Calendar.WEEK_OF_YEAR, -weeks);
        } else if (str.contains("月")) {
            int months = Integer.parseInt(str.substring(0, str.indexOf("个月")));
            pubDate.add(Calendar.MONTH, -months);
        } else if (str.contains("年")) {
            int years = Integer.parseInt(str.substring(0, str.indexOf("年")));
            pubDate.add(Calendar.YEAR, -years);
        }

        return sdf.format(pubDate.getTime());
    }
}
