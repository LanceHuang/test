package com.lance.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void test() {
        ByteBuf byteBuf = Unpooled.buffer(16);
        byteBuf.writeInt(58);
        byteBuf.writeInt(58);
        byteBuf.writeInt(58);
        byteBuf.writeInt(58);
        byteBuf.writeInt(58);
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.writableBytes());
    }

    @Test
    public void testReadableBytes() {
        ByteBuf byteBuf = Unpooled.buffer(64);
        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readableBytes());

        byteBuf.writeShort(10);
        System.out.println(byteBuf.readableBytes());

        byteBuf.writeInt(88);
        System.out.println(byteBuf.readableBytes());

        byteBuf.readShort();
        System.out.println(byteBuf.readableBytes());
    }

    @Test
    public void testSlice() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);
        ByteBuf sliceBuf = byteBuf.slice(1, 5);
        Assert.assertEquals(byteBuf.getByte(3), sliceBuf.getByte(2));

        sliceBuf.setByte(3, 'k');
        System.out.println((char) byteBuf.getByte(4));
    }

    @Test
    public void testReadSlice() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);
        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex() + " " + byteBuf.writerIndex());

        System.out.println("refCnt: " + byteBuf.refCnt());
//        ByteBuf sliceBuf = byteBuf.readSlice(4); //1
        ByteBuf sliceBuf = byteBuf.readSlice(4).retain(); //2
        System.out.println("refCnt: " + byteBuf.refCnt());

        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.readerIndex() + " " + byteBuf.writerIndex());
        System.out.println(sliceBuf.readableBytes());
        System.out.println(sliceBuf.readerIndex() + " " + sliceBuf.writerIndex());

        byteBuf.setByte(1, 'M');
        System.out.println((char) sliceBuf.getByte(1));
    }
}
