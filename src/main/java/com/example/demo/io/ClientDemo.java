package com.example.demo.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1", 8888);

        System.out.println("我要睡20秒。。");
        Thread.sleep(20000l);
        System.out.println("睡醒来了。。");

        OutputStream os = socket.getOutputStream();

        PrintStream ps = new PrintStream(os);

        ps.print("我是客户端1");

        ps.close();

        socket.close();



    }
}
