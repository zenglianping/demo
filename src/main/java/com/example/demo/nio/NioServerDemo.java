package com.example.demo.nio;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServerDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",9000);
        serverSocketChannel.bind(socketAddress);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动完成，等待客户端连接");

        while (true) {
            //阻塞等待通道事件，这个selector与老的serverSocket.accept不同，它可监听多个客户端，不需要等到先连上的客户端读取，才去处理后面的客户端请求
            int evts = selector.select();

            if (evts > 0) { //如果有事件

                //查询事件
                Set<SelectionKey> keys = selector.selectedKeys();

                for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
                    SelectionKey selectionKey =  it.next() ;
                    it.remove();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();;
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        Socket socket = socketChannel.socket();
                        System.out.println("accept a client : " + socket.getInetAddress().getHostName());
                    }

                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        channel.configureBlocking(false);

                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        int read = channel.read(buffer);//read 为读到的字节数
                        if (read > 0) {
                            String msg = new String(buffer.array()).trim();
                            System.out.println("服务器读到数据："+msg);
                        }else{
                            System.out.println("客户端关闭");
                            selectionKey.cancel();
                        }

                    }

                }


            }
        }


    }
}
