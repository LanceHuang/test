package com.lance.test.common.nio;

public class NioServer {
    public static void main(String[] args) {
        //Startup service
        new Thread(new TimeMultiplexerService(8080)).start();
    }
}
