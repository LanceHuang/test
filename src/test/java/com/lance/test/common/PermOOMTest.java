package com.lance.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2016/8/27.
 */
public class PermOOMTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
