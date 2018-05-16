package com.lance.test.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Lance
 * @date 2018/5/16 19:05
 */
public class FTPTest {

    private FTPClient ftpClient;

    @Test
    public void test() throws IOException {
        OutputStream ins = null;
        for (FTPFile file : ftpClient.listFiles()) {
            System.out.println(file.getName());
            if (file.isFile()) {
                ins = new BufferedOutputStream(new FileOutputStream("f:/tmp/unpack/" + file.getName()));
                ftpClient.retrieveFile(file.getName(), ins);
                ins.close();
            }
        }
    }

    @Before
    public void before() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.138.132");
        ftpClient.login("ftp1", "123456");
        System.out.println(ftpClient.getReplyCode());
    }

    @After
    public void after() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
    }
}
