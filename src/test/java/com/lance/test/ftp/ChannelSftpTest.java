package com.lance.test.ftp;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Lance
 * @date 2018/2/23 15:04
 */
public class ChannelSftpTest {

    @Test
    public void test() {
        JSch jSch = new JSch();
        Session session = null;
        ChannelSftp channel = null;
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session = jSch.getSession("lance", "192.168.138.131", 22);
            session.setPassword("123456");
            session.setConfig(config);
            session.connect();

            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            channel.cd("/home/lance");
            InputStream in = channel.get("test.txt");
            for (String line : IOUtils.readLines(new InputStreamReader(in))) {
                System.out.println(line);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != channel) {
                channel.exit();
            }
            if (null != session) {
                session.disconnect();
            }
        }
    }
}
