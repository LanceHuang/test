package com.lance.test.common.nio;

public class NioClient {
    public static void main(String[] args) {
        //Startup client
        new Thread(new TimeMultiplexerClient("127.0.0.1", 8080)).start();
    }
}
