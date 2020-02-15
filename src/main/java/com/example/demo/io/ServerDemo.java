package com.example.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 基于流的Server端
 */
public class ServerDemo {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("服务器启动。。。");

        while (true) {// 不断地去接收客户请求

            System.out.println("接收请求中。。。");

            //监听，先连接上的，一定要等读到数据，才会让下一次监听到的客户端读取
            Socket socket = serverSocket.accept();

            System.out.println("读取数据中。。。");

            //读取数据会阻塞，直接客户端有数据写入
            InputStream in = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = bufferedReader.readLine();

            System.out.println("服务器读到："+line);
        }





    }
}
