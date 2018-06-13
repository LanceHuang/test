package com.lance.test.common.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TimeMultiplexerService implements Runnable {

    /**
     * Listener
     */
    private Selector selector;

    private boolean stop;

    public TimeMultiplexerService(int port) {
        try {
            //Create selector
            selector = Selector.open();

            //Create server channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            //Register server channel
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Ready to used");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //Select "Ready" state's channel, and add to publicSelectedKeys
                selector.select(1000);
                System.out.println("Select");
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Loop
            for (Iterator<SelectionKey> keyItr = selector.selectedKeys().iterator(); keyItr.hasNext(); ) {
                SelectionKey key = keyItr.next();
                keyItr.remove();

                if (!key.isValid()) {
                    continue;
                }

                try {
                    //Handle: connect, read
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        continue;
                    }

                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        int count = sc.read(buff);

                        if (count > 0) {
                            buff.flip();
                            byte[] data = new byte[buff.remaining()];
                            buff.get(data);

                            System.out.println("Msg: " + (new String(data)));

                            String returnMsg = "Hi, " + (new Date());
                            ByteBuffer returnMsgBuff = ByteBuffer.allocate(returnMsg.length());
                            returnMsgBuff.put(returnMsg.getBytes());
                            returnMsgBuff.flip();
                            sc.write(returnMsgBuff);
                        } else if (count < 0) {
                            key.cancel();
                            sc.close();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    if (null != key) {
                        key.cancel();
                        try {
                            if (null != key.channel()) {
                                key.channel().close();
                            }
                        } catch (IOException e1) {
                            //Do nothing
                        }
                    }
                }
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

    public void stop() {
        this.stop = true;
    }


}
