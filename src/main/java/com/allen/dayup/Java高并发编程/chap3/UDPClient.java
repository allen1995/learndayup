package com.allen.dayup.Java高并发编程.chap3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UDPClient {

    public void send() throws IOException {

        //获取DatagranChannel
        DatagramChannel channel = DatagramChannel.open();
        //设置为非阻塞模式
        channel.configureBlocking(false);

        //创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(2048);

        Scanner scanner = new Scanner(System.in);

        System.out.println("UDP客户端启动成功！");
        System.out.println("请输入要发送的内容:");

        while ( scanner.hasNext() ) {
            String text = scanner.next();

            buf.put( (LocalDateTime.now() + ">>" + text)
                    .getBytes(StandardCharsets.UTF_8));
            buf.flip();

            channel.send(buf, new InetSocketAddress("127.0.0.1", 18080));
            buf.clear();
        }

        channel.close();

    }

    public static void main(String[] args) throws IOException {
        //启动客户端
        new UDPClient().send();
    }

}
