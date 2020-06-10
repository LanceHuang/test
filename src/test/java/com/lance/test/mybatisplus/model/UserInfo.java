package com.lance.test.mybatisplus.model;

/**
 * @author Lance
 */
public class UserInfo {

    private int type;
    private int num;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "type=" + type +
                ", num=" + num +
                '}';
    }
}
