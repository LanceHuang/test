package com.lance.test.digester;

/**
 * @author Lance
 * @since 2019/1/21 14:45
 */
public class Role {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
