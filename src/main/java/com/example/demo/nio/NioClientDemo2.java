package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端2
 */
public class NioClientDemo2 {

    public static void main(String[] args) throws IOException {
        //客户端使用SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9000);
        System.out.println("客户端2正在连接");
        socketChannel.connect(socketAddress);
        System.out.println("客户端2连接成功");

//        while (true) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                SelectionKey selectionKey =  it.next() ;
                it.remove();

                if (selectionKey.isConnectable()) {
                    //如果连接了，就写数据，并标记通道为可读
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("我是客户端2").getBytes()));
                }
            }
//        }


    }
}
