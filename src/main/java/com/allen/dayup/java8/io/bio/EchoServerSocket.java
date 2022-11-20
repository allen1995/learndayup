package com.allen.dayup.java8.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: 20190598
 * @Date: 2022/11/18 17:21
 * @Description:
 */
public class EchoServerSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);


        Socket socket;
        while (true) {

            System.out.println("服务监听启动");
            socket = serverSocket.accept();

            // 处理socket

            Socket finalSocket = socket;
            CompletableFuture.runAsync( () -> {
                try {
                    handle(finalSocket);
                    serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }

    }

    private static void handle(Socket socket) throws IOException {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        PrintWriter out = null;
        try {
            inputStream = socket.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String body = null;

            while ((body = bufferedReader.readLine())!=null && body.length()!=0){
                System.out.println("the time server receive msg :" + body);
                out.println(new Date().toString());
            }

            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.write("response msg");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if( inputStream != null ) {
                inputStream.close();
            }

            if( bufferedReader != null ) {
                bufferedReader.close();
            }

            if( out != null ) {
                out.close();
            }

            if( socket != null ) {
                socket.close();
            }
        }
    }
}
