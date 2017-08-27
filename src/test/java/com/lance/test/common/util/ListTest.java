package com.lance.test.common.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2016/8/11.
 */
public class ListTest {

    @Test
    public void testToArray() {
        List<String> list = new ArrayList<String>();
        list.add("Lancce");
        list.add("dsfsdfscce");
        list.add("ancce");
        list.add("Lanase");
        String[] strArr = list.toArray(new String[0]);
        System.out.println(strArr.length);
    }
}
