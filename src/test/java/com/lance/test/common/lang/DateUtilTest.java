package com.lance.test.common.lang;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lance
 * @since 2019/3/25 9:59
 */
public class DateUtilTest {

    @Test
    public void test() {
        Date now = new Date();
        Date nextDate = DateUtils.addDays(now, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
        System.out.println(sdf.format(nextDate));
        System.out.println(DateUtils.isSameDay(now, nextDate));
    }
}

