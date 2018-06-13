package com.lance.test.common.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TimeMultiplexerClient implements Runnable {

    private Selector selector;
    private SocketChannel sc;

    private boolean stop;

    private String host;
    private int port;

    public TimeMultiplexerClient(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            //Create selector
            selector = Selector.open();

            //Register channel
            sc = SocketChannel.open();
            sc.configureBlocking(false);
//            sc.bind(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (sc.connect(new InetSocketAddress(host, port))) {
                sc.register(selector, SelectionKey.OP_READ);
                String msg = "hello kugou";
                ByteBuffer buff = ByteBuffer.allocate(msg.length());
                buff.put(msg.getBytes());
                buff.flip();
                sc.write(buff);
            } else {
                sc.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (!stop) {
            try {
                selector.select(1000);

                //Loop
                for (Iterator<SelectionKey> keyItr = selector.selectedKeys().iterator(); keyItr.hasNext(); ) {
                    SelectionKey key = keyItr.next();
                    keyItr.remove();

                    //Handle: connect, write
                    if (!key.isValid()) {
                        continue;
                    }

                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        if (!socketChannel.finishConnect()) {
                            break;
                        }

                        socketChannel.register(selector, SelectionKey.OP_READ);
                        String msg = "hello kugou";
                        ByteBuffer buff = ByteBuffer.allocate(msg.length());
                        buff.put(msg.getBytes());
                        buff.flip();
                        socketChannel.write(buff);
                    }

                    if (key.isReadable()) {
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        int count = socketChannel.read(buff);
                        if (count > 0) {
                            buff.flip();
                            byte[] data = new byte[buff.remaining()];
                            buff.get(data);
                            System.out.println("Data: " + (new String(data)));
                            stop = true;
                        } else if (count < 0) {
                            key.cancel();
                            socketChannel.close();
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != selector) {
            try {
                selector.close();
            } catch (IOException e) {
                //Do nothing
            }
        }

    }
}
