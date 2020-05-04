package com.lance.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Lance
 */
public class Server {

    public static void main(String[] args) {
        int port = 8080;

        EventLoopGroup boss = new NioEventLoopGroup(); // while
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class) // ServerSocket
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerChannelHandler());
                    }
                });

        try {
            ChannelFuture f = b.bind().sync();
            System.out.println("Startup Server.");
            f.channel().closeFuture().sync();
            System.out.println("Shutdown Server.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 不知道要不要加sync()
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
