package com.lance.test.mongodb;

/**
 * 装备
 *
 * @author Lance
 */
public class Equipment {

    private int  type;
    private int  pos;
    private long defense;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public long getDefense() {
        return defense;
    }

    public void setDefense(long defense) {
        this.defense = defense;
    }
}
