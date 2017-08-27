package com.lance.test.common.lang;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lance on 2016/8/12.
 */
public class RuntimeTest {

    @Test
    public void testAddShutdownHook() {
        System.out.println("Hello Runtime");

        final Runtime rt = Runtime.getRuntime();

        rt.addShutdownHook(new Thread() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();

                long heapMaxSize = rt.maxMemory() >> 20;
                sb.append("Max heap size:" + heapMaxSize + "M\n");
                long total = rt.totalMemory() >> 20;
                sb.append("Total size:" + total + "M\n");
                long free = rt.freeMemory() >> 20;
                sb.append("Free size:" + free + "M\n");

                System.out.println(sb.toString());
            }
        });

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 300000; i++) {
            map.put("key" + i, "value" + i);
        }
    }

    @Test
    public void test() {
        String cmd = "mvn";
        String outputFilename = "E:\\tmp\\hh.txt";

        try {
            Process process = Runtime.getRuntime().exec(cmd);
//            process.getOutputStream();


            BufferedReader buffReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter out = new PrintWriter(new FileOutputStream(outputFilename));


            BufferedReader buffErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));


//            byte[] buff = new byte[1024];
//            int count = 0;
            String line = "";

            while ((line = buffReader.readLine()) != null) {
//                System.out.println("结果："+new String(buff,0,count));
                System.out.println(line);
//                out.write(buff, 0, count);
                out.println(line);
            }

            while ((line = buffErr.readLine()) != null) {
//                System.out.println("结果："+new String(buff,0,count));
                System.out.println(line);
//                out.write(buff, 0, count);
            }


            out.flush();
            out.close();
            buffReader.close();
            buffErr.close();
            int exitVal = process.waitFor();
            System.out.println(exitVal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
