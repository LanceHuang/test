package com.lance.test.common.time;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * JDK8新增的时间类
 *
 * <ol>
 *     <li>Date只能获取时间，LocalDateTime提供更多API操作时间</li>
 *     <li>SimpleDateFormat线程不安全，DateTimeFormatter线程安全</li>
 * </ol>
 *
 * @author Lance
 * @since 2022/3/31
 */
public class LocalDateTimeTest {

    @Test
    public void testFormat() {
        String strFormat = "yyyy-MM-dd HH:mm:ss";

        // Not thread safe
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        System.out.println(sdf.format(nowDate));

        // Thread safe
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(strFormat);
        System.out.println(dtf.format(nowLocalDateTime));
    }

    @Test
    public void testApi() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.printf("%d-%d-%d\n", localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        System.out.printf("%d:%d:%d\n", localTime.getHour(), localTime.getMinute(), localTime.getSecond());

        LocalDate newLocalDate = localDate.plusDays(20);
        LocalTime newLocalTime = localTime.plusMinutes(100);
        System.out.printf("%d-%d-%d\n", newLocalDate.getYear(), newLocalDate.getMonthValue(), newLocalDate.getDayOfMonth());
        System.out.printf("%d:%d:%d\n", newLocalTime.getHour(), newLocalTime.getMinute(), newLocalTime.getSecond());
    }
}
