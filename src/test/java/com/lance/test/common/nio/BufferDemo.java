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
        buffer.put(30, (byte) 78);// 报错
        sop(buffer);
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
        sop(buffer);

        FileOutputStream outputStream = null;
        FileChannel fc = null;
        try {
            // 输出缓冲区内容到文件
            outputStream = new FileOutputStream("D:\\note\\test\\NIO笔记.txt");
            fc = outputStream.getChannel();
            fc.write(buffer);
            sop(buffer);
        } catch (IOException e) {
            System.out.println("缓冲区读取失败");
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                System.err.println("关闭通道时发生错误");
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                System.err.println("初始化输出流错误");
            }
        }
    }

    @Test
    public void testChannelRead() {
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileInputStream inputStream = null;
        FileChannel channel = null;
        try {
            // 创建文件流
            inputStream = new FileInputStream("D:\\note\\test\\NIO笔记.txt");

            // 创建通道
            channel = inputStream.getChannel();

            // 将文件数据读入缓冲区
            int num = channel.read(buffer);
            System.out.println("已读数据：" + num);

            // 输出缓冲区数据
            for (int i = 0; i < 20; i++) {
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
    public static void sop(Buffer buffer) {
        System.out.println("Position=" + buffer.position() + ", Limit="
                + buffer.limit() + ", Capacity=" + buffer.capacity());
        System.out.println();
    }
}
