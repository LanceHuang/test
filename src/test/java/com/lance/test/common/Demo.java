package com.lance.test.common;

import lombok.*;

import static com.lance.common.tool.util.ConsolePrinter.*;

public class Demo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Lance");
        println(p);
    }
}

@Getter
@Setter
class Person {
    private String name;
    private int age;
}
