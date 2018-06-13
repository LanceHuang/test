package com.lance.test.common.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockServer {
    public static void main(String[] args) {
        //Listen 8080
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Ready to accept connections...");

            while (true) {
                //Accept socket
                Socket socket = serverSocket.accept();
                System.out.println("Accept connection");
                //Create handler
                new Thread(new SocketHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Release connection
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    //Do nothing
                }
            }
        }
    }
}
