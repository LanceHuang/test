package com.lance.test.common.entity;

/**
 * @author Lance
 * @date 2016/11/30
 */
public enum Color {
    RED("RED"),
    YELLOW("YELLOW"),
    BLUE("BLUE"),
    GREEN("GREEN");

    private String name;

    Color(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }
}
