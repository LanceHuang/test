package com.lance.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            System.out.println("Ready");
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientChannelHandler());
                        }
                    });

            System.out.println("Go");
            ChannelFuture f = b.connect().sync();

            System.out.println("OK");
            f.channel().closeFuture().sync();
            System.out.println("Finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully().sync();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost", 8899).run();
    }
}
