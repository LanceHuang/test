package com.lance.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

public class EmbeddedChannelTest {

    @Test
    public void test() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(5));
        System.out.println(embeddedChannel.writeInbound(Unpooled.buffer(16).writeInt(58)));
        System.out.println(embeddedChannel.finish());

        ByteBuf byteBuf = embeddedChannel.readInbound();
        System.out.println(byteBuf.readableBytes());
        byteBuf.release();

        Assert.assertFalse(embeddedChannel.finish());
    }

    @Test
    public void testCodec() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ArticleCodec());

        System.out.println(embeddedChannel.writeInbound(
                Unpooled.buffer(16).writeInt(99).writeInt(85)
        ));
        //consume
        embeddedChannel.readInbound();

        System.out.println(embeddedChannel.writeInbound(
                Unpooled.buffer(16).writeInt(99)
        ));
    }

}
