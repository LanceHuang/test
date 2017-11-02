package com.lance.test.common;


import java.util.Comparator;

public class Demo {
    public static void main(String[] args) {
        sop((a) -> a + "h");
    }

    public static void sop(Printer p) {
        System.out.println(p.print("hi"));
    }
}

@FunctionalInterface
interface Printer {
    String print(String msg);
}