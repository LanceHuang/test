package com.lance.test.common.nio;

/**
 * @author Lance
 */
public class MyByteBuffer {

    private int position;
    private int limit;
    private int capacity;

    private byte[] data;

    public MyByteBuffer(int capacity) {
        this.position = 0;
        this.limit = capacity;
        this.capacity = capacity;
        this.data = new byte[capacity];
    }

    public MyByteBuffer put(byte b) {
        if (this.position >= this.limit) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data[this.position++] = b;
        return this;
    }

    public MyByteBuffer put(int index, byte b) {
        if (index < 0 || index >= this.capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data[index] = b;
        return this;
    }

    public byte get() {
        if (this.position >= this.limit) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.data[this.position++];
    }

    public byte get(int index) {
        if (index < 0 || index >= this.capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.data[index];
    }

    public void flip() {
        this.limit = this.position;
        this.position = 0;
    }

    public void compact() {
        int remaining = this.limit - this.position;
        System.arraycopy(this.data, this.position, this.data, 0, remaining);
        this.position = remaining;
        this.limit = this.capacity;
    }

    public void clear() {
        this.position = 0;
        this.limit = this.capacity;
    }

    public int getPosition() {
        return position;
    }

    public int getLimit() {
        return limit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void sop() {
        System.out.println("position=" + this.position + ", limit=" + this.limit + ", capacity=" + this.capacity);
        for (byte d : this.data) {
            System.out.print(d + " ");
        }
        System.out.println();
    }
}
