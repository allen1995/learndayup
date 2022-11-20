package com.allen.dayup.Java高并发编程.chap3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPServer {

    public static void receive() throws IOException {
        //获取DataggramChannel
        DatagramChannel sChannel = DatagramChannel.open();
        //设置为非阻塞模式
        sChannel.configureBlocking(false);
        //绑定监听地址
        sChannel.bind(new InetSocketAddress("127.0.0.1", 18080));

        System.out.println("UDP服务器启动成功");

        //开启一个选择器通道
        Selector selector = Selector.open();
        //将通道注册到Channel
        sChannel.register(selector, SelectionKey.OP_READ);

        //通过通道选择查询IO事件
        while ( selector.select() > 0 ) {
            Iterator<SelectionKey> iterator = selector.selectedKeys()
                    .iterator();

            ByteBuffer buf = ByteBuffer.allocate(2048);

            //迭代IO事件
            while ( iterator.hasNext() ) {
               SelectionKey selectionKey = iterator.next();

               //可读事件，有数据到来
               if( selectionKey.isReadable() ) {
                   //读取datagramChannel数据
                   SocketAddress clent = sChannel.receive(buf);

                   //切换buf为读模式
                   buf.flip();

                   System.out.println(new String(buf.array(), 0, buf.limit()));

                   buf.clear();
               }
            }

            iterator.remove();
        }

        //关闭通道和选择器
        selector.close();
        sChannel.close();


    }

    public static void main(String[] args) throws IOException {
        new UDPServer().receive();
    }
}
