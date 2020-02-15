package com.example.demo.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientDemo2 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1", 8888);

        OutputStream os = socket.getOutputStream();

        PrintStream ps = new PrintStream(os);

        ps.print("我是客户端2");

        ps.close();

        socket.close();

        System.out.println("我已经发送了请求");


    }
}
