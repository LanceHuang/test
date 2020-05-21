package com.lance.test.common.nio;

import org.junit.jupiter.api.Test;

class MyByteBufferTest {

    @Test
    public void test() {
        MyByteBuffer byteBuffer = new MyByteBuffer(32);
        byteBuffer.sop();

        byteBuffer.put((byte) 'A');
        byteBuffer.put((byte) 'B');
        byteBuffer.put((byte) 'C');
        byteBuffer.put((byte) 'D');
        byteBuffer.put((byte) 'E');
        byteBuffer.sop();
        byteBuffer.put(0, (byte) 'F');
        byteBuffer.sop();

        byteBuffer.flip();
        byteBuffer.sop();

        System.out.println(byteBuffer.get());
        byteBuffer.sop();
        System.out.println(byteBuffer.get(3));
        byteBuffer.sop();

        byteBuffer.compact();
        byteBuffer.sop();

        byteBuffer.clear();
        byteBuffer.sop();
    }

}