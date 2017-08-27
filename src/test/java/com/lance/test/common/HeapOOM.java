package com.lance.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zzm
 */
public class HeapOOM {
    static class OOMbject {
    }

    public static void main(String[] args) {
        List<OOMbject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMbject());
        }
    }
}