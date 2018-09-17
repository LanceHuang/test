package com.lance.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

class ArticleServerChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Article article = new Article();
        article.setTotalPage(100);
        article.setCurrPage(54);
        System.out.println("Ready to send: " + article);

        ctx.writeAndFlush(article);
    }
}
