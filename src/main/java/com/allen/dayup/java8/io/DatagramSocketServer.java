package com.allen.dayup.java8.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: allen
 * @Date: 2020-04-06 17:03
 * @Description:
 */
public class DatagramSocketServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(7777);
        for(;;){
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            ds.receive(packet);
            //转换收到的数据
            String s = new String(packet.getData(),packet.getOffset(),packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("收到数据:" + s);
            //发送数据
            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            ds.send(packet);
        }
    }
}
