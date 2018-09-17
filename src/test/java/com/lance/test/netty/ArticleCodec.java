package com.lance.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

class ArticleCodec extends ByteToMessageCodec<Article> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Article msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getTotalPage());
        out.writeInt(msg.getCurrPage());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 8) {
            Article article = new Article();
            article.setTotalPage(in.readInt());
            article.setCurrPage(in.readInt());
            System.out.println("Decode: " + article);

            out.add(article);
        }
    }
}
