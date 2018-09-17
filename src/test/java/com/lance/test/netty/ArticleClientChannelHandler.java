package com.lance.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

class ArticleClientChannelHandler extends SimpleChannelInboundHandler<Article> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Article msg) throws Exception {
        System.out.println("Receive: " + msg);
    }
}
