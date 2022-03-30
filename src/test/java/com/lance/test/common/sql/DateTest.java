package com.lance.test.common.sql;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * java.sql.Date为了符合SQL的日期格式，专门封装了一个类，覆写了toString方法
 *
 * @author Lance
 * @since 2022/3/30
 */
public class DateTest {

    @Test
    void test() {
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(utilDate);
        System.out.println(sqlDate);
//        Wed Mar 30 17:57:34 CST 2022
//        2022-03-30
    }
}
