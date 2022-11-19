package com.allen.dayup.java8.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ServerSocket server;
    public static String IP = "127.0.0.1";
    public static int PORT = 12378;

    public  static ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static void start() throws IOException {

        try {

               server = new ServerSocket(PORT);
            while (true) {
                System.out.println("建立新连接");

                Socket socket = server.accept();
                executorService.execute(new ServerHandle(socket));
            }
            //Socket socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(server!=null){
                server.close();
            }
        }



    }

    public static void main(String[] args) throws IOException {
        start();
    }
}
