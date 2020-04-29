package com.lance.test.common.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <ol>
 *     <li>testServer1()+testClient()跟BIO一样，可以正常使用</li>
 *     <li>testServer2()启动后，accept返回值为null，然后会报NPE</li>
 *     <li>testServer3()+testClient()可连接成功，但是读不到数据，count==0</li>
 * </ol>
 *
 * @author Lance
 */
public class NioTest {
    // ======== BIO =======
    // 1. Accept and read（单线程）
    // 2. Accept and Thread.start（多线程）
    // 3. Accept and poll（线程池）
    // 线程池：解决accept阻塞，读写阻塞，且控制线程数

    private String hostname = "127.0.0.1";
    private int    port     = 8080;

    @Test
    public void testServer1() throws IOException {
        // 1. new ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("Ready to accept");

        // 2. ServerSocket.accept
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Accept: " + socketChannel);

        // 3. Socket.getInputStream().read(byte[])
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(buffer);
        System.out.println("Read: " + count);
        System.out.print(buffer.getChar(0));
        System.out.print(buffer.getChar(2));
        System.out.print(buffer.getChar(4)); // 从index=4的byte开始，读两个byte，合成char并返回
        System.out.print(buffer.getChar(6));
        System.out.print(buffer.getChar(8));
        System.out.println();

        // 4. close
        socketChannel.close();
        serverSocketChannel.close();
        System.out.println("Finished.");
    }

    @Test
    public void testServer2() throws IOException {
        // 1. new ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        System.out.println("Ready to accept");

        // 2. ServerSocket.accept
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Accept: " + socketChannel);

        // 3. Socket.getInputStream().read(byte[])
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(buffer); // 这里会报空指针，因为配置了serverSocketChannel.configureBlocking(false)，所以上面的socketChannel==null
        System.out.println("Read: " + count);
        System.out.print(buffer.getChar(0));
        System.out.print(buffer.getChar(2));
        System.out.print(buffer.getChar(4)); // 从index=4的byte开始，读两个byte，合成char并返回
        System.out.print(buffer.getChar(6));
        System.out.print(buffer.getChar(8));
        System.out.println();

        // 4. close
        socketChannel.close();
        serverSocketChannel.close();
        System.out.println("Finished.");
    }

    @Test
    public void testServer3() throws IOException {
        // 1. new ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("Ready to accept");

        // 2. ServerSocket.accept
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Accept: " + socketChannel);
        socketChannel.configureBlocking(false);

        // 3. Socket.getInputStream().read(byte[])
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = socketChannel.read(buffer); // 这里count==0，因为配置了socketChannel.configureBlocking(false)，还没读到数据就返回了
        System.out.println("Read: " + count);
        System.out.print(buffer.getChar(0));
        System.out.print(buffer.getChar(2));
        System.out.print(buffer.getChar(4)); // 从index=4的byte开始，读两个byte，合成char并返回
        System.out.print(buffer.getChar(6));
        System.out.print(buffer.getChar(8));
        System.out.println();

        // 4. close
        socketChannel.close();
        serverSocketChannel.close();
        System.out.println("Finished.");
    }

    @Test
    public void testClient() throws IOException {
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
        buffer.flip(); // 切换写模式
        int count = socketChannel.write(buffer);
        System.out.println("Write: " + count);

        // 3. close
        socketChannel.close();
        System.out.println("Finished.");
    }
}
