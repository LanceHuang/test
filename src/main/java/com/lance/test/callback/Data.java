package com.lance.test.callback;

public class Data {
    private String username;
    private int age;

    public Data(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Data{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
