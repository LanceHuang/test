package com.lance.test.common.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {

    @Test
    public void testPut() {
        String message = "Hello NIO";
        byte[] msgByte = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 一次一个输入
        for (byte b : msgByte) {
            buffer.put(b);
        }
        sop(buffer);

        // 一次性输入
        buffer.put(msgByte);
        sop(buffer);

        buffer.put(10, (byte) 78);
        sop(buffer);

        buffer.put((byte) 78);
        sop(buffer);

        buffer.flip();// 使得limit=position，以便测试index>=limit
        buffer.put((byte) 78);
        sop(buffer);
        buffer.put(30, (byte) 78);// 报错
    }

    @Test
    public void testClear() {
        String message = "Hello NIO";
        byte[] msgByte = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(msgByte);
        sop(buffer);

        buffer.flip();
        sop(buffer);

        buffer.clear();
        sop(buffer);
    }

    @Test
    public void testGet() {
        String message = "Hello NIO";
        byte[] msgByte = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(msgByte);
        sop(buffer);

        System.out.println(buffer.get());
        sop(buffer);

        System.out.println(buffer.get(0) + "-" + (char) buffer.get(0));
        sop(buffer);

        buffer.flip();// 使得limit=position，以便试验当index>=limit
        System.out.println(buffer.get(buffer.limit() - 1) + "-"
                + (char) buffer.get(buffer.limit() - 1));
        System.out.println(buffer.get(buffer.limit()) + "-"
                + (char) buffer.get(buffer.limit()));// 报错，说明0<=index<limit
        sop(buffer);
    }

    @Test
    public void testFlip() {
        String message = "Hello NIO";
        byte[] msgByte = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(msgByte);
        sop(buffer);

        buffer.flip();
        sop(buffer);
    }

    @Test
    public void testChannelWrite() {
        String message = "Hello NIO";
        byte[] msgByte = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(msgByte);
        sop(buffer);//Position=9, Limit=1024, Capacity=1024

        FileOutputStream outputStream = null;
        FileChannel fc = null;
        try {
            // 输出缓冲区内容到文件
            outputStream = new FileOutputStream("f:\\test\\NIO.txt");
            fc = outputStream.getChannel();

            buffer.flip();
//            buffer.clear();//输出包括“Hello NIO”在内的内容
            sop(buffer);//Position=0, Limit=9, Capacity=1024

            fc.write(buffer);
            sop(buffer);//Position=9, Limit=9, Capacity=1024
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("缓冲区读取失败");
        } finally {
            try {
                if (null != fc) {
                    fc.close();
                }
            } catch (IOException e) {
                System.err.println("关闭通道时发生错误");
            }
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.err.println("初始化输出流错误");
            }
        }
    }

    @Test
    public void testChannelRead() {
        // Create buffer area
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileInputStream inputStream = null;
        FileChannel channel = null;
        try {
            inputStream = new FileInputStream("f:\\test\\NIO.txt");
            channel = inputStream.getChannel();

            // Read data from channel
            int num = channel.read(buffer);
            System.out.println("已读数据：" + num);

            // 输出缓冲区数据
            for (int i = 0; i < num; i++) {
                System.out.println(buffer.get(i) + "-" + (char) buffer.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("通道读取缓冲区数据失败");
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    System.err.println("通道关闭失败");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("文件流关闭失败");
                }
            }
        }

    }

    @Test
    public void testSlice() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // Slice
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer sliceBuffer = buffer.slice();
        sop(sliceBuffer);

        // AsReadOnlyBuffer
        sliceBuffer.asReadOnlyBuffer();// 仅调用，看看会不会发生效果
        sliceBuffer.put((byte) 77);
        // 往调用asReadOnlyBuffer后返回的缓冲区放入数据，看会不会出现效果
        sliceBuffer.asReadOnlyBuffer().put((byte) 77);
    }

    /**
     * 输出position-limit-capacity
     */
    private void sop(Buffer buffer) {
        System.out.println("Position=" + buffer.position() + ", Limit="
                + buffer.limit() + ", Capacity=" + buffer.capacity());
        System.out.println();
    }
}
