package com.lance.test.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.junit.Assert;
import org.junit.Test;

public class NettyProtobufTest {

    @Test
    public void test() {
        EmbeddedChannel testOutboundChannel = new EmbeddedChannel(
                new ProtobufVarint32LengthFieldPrepender(), //3
                new ProtobufEncoder(), //2
                new UserEchoChannelHandler() //1
        );

        User.UserInfo userInfo = User.UserInfo.newBuilder()
                .setName("Lance")
                .setAge(24)
                .build();

        Assert.assertFalse(testOutboundChannel.writeInbound(userInfo));
        Assert.assertTrue(testOutboundChannel.finish());

        ByteBuf byteBuf = testOutboundChannel.readOutbound();
        System.out.println("ByteBuf size: " + byteBuf.readableBytes());


        EmbeddedChannel testInboundChannel = new EmbeddedChannel(
                new ProtobufVarint32FrameDecoder(),//1
                new ProtobufDecoder(User.UserInfo.getDefaultInstance()),//2
                new ProtobufVarint32LengthFieldPrepender(),//5
                new ProtobufEncoder(),//4
                new UserEchoChannelHandler()//3
        );
        System.out.println(testInboundChannel.writeInbound(byteBuf));
    }
}

class UserEchoChannelHandler extends SimpleChannelInboundHandler<User.UserInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User.UserInfo msg) throws Exception {
        System.out.println("Receive: " + msg);
        ctx.channel().writeAndFlush(msg);
    }
}