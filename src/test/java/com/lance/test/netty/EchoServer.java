package com.lance.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            System.out.println("Ready");
            ServerBootstrap b = new ServerBootstrap();
            b.group(workerGroup)//Listen to connect, read, write
                    .channel(NioServerSocketChannel.class) //Channel type
                    .localAddress(port) //Listen localhost's port
                    .childHandler(new ChannelInitializer<Channel>() { // Like DispatcherServlet
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerChannelHandler());//Like mapping
                        }
                    });

            System.out.println("Go");
            ChannelFuture f = b.bind().sync(); //Tomcat startup
            System.out.println("Ready to use: " + f.channel().localAddress());

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully().sync();
//            bossGroup.shutdownGracefully();
        }
        System.out.println("Finished");
    }


    public static void main(String[] args) throws Exception {
        //Simplify for test
        new EchoServer(8890).run();
    }
}
