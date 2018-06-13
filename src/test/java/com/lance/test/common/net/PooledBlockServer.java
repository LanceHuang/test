package com.lance.test.common.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledBlockServer {
    public static void main(String[] args) {
        //Listen 8080
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Ready to accept connections...");
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                //Accept socket
                Socket socket = serverSocket.accept();
                System.out.println("Accept connection");
                //Create handler

                executorService.submit(new SocketHandler(socket));
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
