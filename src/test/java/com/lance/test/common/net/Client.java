package com.lance.test.common.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        //Connect to server
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            System.out.println("Connect to server");

            //Send directives
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Query time");

            //Read message
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Release connection
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != socket) {
                    socket.close();
                }
            } catch (IOException e) {
                //Do nothing
            }

        }

    }
}
