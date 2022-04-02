//package com.lance.test.hadoop;
//
//import com.lance.common.tool.FileUtils;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
//import org.junit.Test;
//
//import java.io.*;
//import java.net.URL;
//
///**
// * @author Lance
// * @since 2017/2/3
// */
//public class HDFSTest {
//
//    @Test
//    public void testURLRead() {
//        InputStream in = null;
//        try {
//            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
//            in = new URL("hdfs://master01:9000/user/hadoop/test.txt").openStream();
//
//            IOUtils.copyBytes(in, System.out, 4096, false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeStream(in);
//        }
//    }
//
//    @Test
//    public void testRead() {
//        String url = "hdfs://192.168.147.128:9000";
//        String intputDir = "/user/hadoop/input/protocols";
//        String outputDir = "e:/protocols.txt";
//
//        FileSystem fs = null;
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//            //Config and open
//            Configuration conf = new Configuration();
//            conf.set("fs.default.name", url);
//            fs = FileSystem.get(conf);
//            in = fs.open(new Path(intputDir));
//
//            //Read and output
//            out = new FileOutputStream(outputDir);
//            FileUtils.copyAndClose(in, out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            FileUtils.closeQuietly(fs);
//        }
//
//        System.out.println("Finished.");
//    }
//
//    @Test
//    public void testWrite() {
//        String url = "hdfs://192.168.147.128:9000";
//        String intputDir = "e:/隐藏文件.txt";
//        String outputDir = "input/hide";
////        String outputDir = "/user/hadoop/input/hide";
//
//        FileSystem fs = null;
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//            Configuration conf = new Configuration();
//            conf.set("fs.default.name", url);
//            fs = FileSystem.get(conf);
//
//            in = new FileInputStream(intputDir);
//            out = fs.create(new Path(outputDir));
//            FileUtils.copyAndClose(in, out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            FileUtils.closeQuietly(fs);
//        }
//
//        System.out.println("Finished.");
//    }
//
//}
