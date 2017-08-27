package com.lance.test.common.util;

import java.util.TimeZone;

import org.junit.Test;

public class TimeZoneTest {

    @Test
    public void testClone() {
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getID());

        TimeZone zoneCp = (TimeZone) timeZone.clone();
        System.out.println(zoneCp.getID());

        timeZone.setID("New ID");
        System.out.println("After changing id...");
        System.out.println(timeZone.getID());
        System.out.println(zoneCp.getID());

        System.out.println(timeZone.getDisplayName());
//        System.out.println(timeZone.toZoneID().);
    }
}
