package com.lance.test.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Lance
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String hostname = "127.0.0.1";
        int port = 8080;

        // 1. new Socket
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(hostname, port));
        System.out.println("Connect: " + socketChannel);

        // 2. Write
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putChar('H');
        buffer.putChar('e');
        buffer.putChar('l');
        buffer.putChar('l');
        buffer.putChar('o');
        buffer.flip(); // 切换到读模式
        int count = socketChannel.write(buffer);
        System.out.println("Write: " + count);

        // 3. close
        socketChannel.close();
        System.out.println("Finished.");
    }
}
