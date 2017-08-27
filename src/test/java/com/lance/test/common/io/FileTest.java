package com.lance.test.common.io;

import org.junit.Test;

import java.io.File;

/**
 * @author Lance
 * @date 2016/10/26 17:43
 */
public class FileTest {

    @Test
    public void testGetName() {
        String filename = "E:\\test\\fenci.txt";
        File file = new File(filename);
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getParent());

//        try {
//            RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println(rt.maxMemory() >> 20);
                System.out.println(rt.totalMemory() >> 20);
                System.out.println(rt.freeMemory() >> 20);
            }
        });
    }

    @Test
    public void testRename() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\MessageEntity.java");
        file.renameTo(new File("MessageEntity2.java"));

        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println(rt.maxMemory() >> 20);
                System.out.println(rt.totalMemory() >> 20);
                System.out.println(rt.freeMemory() >> 20);
            }
        });
    }
}
