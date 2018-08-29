package com.lance.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void test() {
        ByteBuf byteBuf = Unpooled.buffer(64);
        byteBuf.writeShort(24947);
        System.out.println(byteBuf.readShort());
    }
}
