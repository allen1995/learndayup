package com.allen.dayup.java8.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String IP = "127.0.0.1";
    public static int PORT = 12378;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PORT);

            System.out.println("请输出数据");
            new ReadMsg(socket).start();


            while (true) {
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(new Scanner(System.in).next());
                writer.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class ReadMsg extends Thread {
        private Socket socket;

        ReadMsg(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                BufferedReader reader1 = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                String line = null;
                while ((line = reader1.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                clear();
            }

        }
        //必要的资源清理工作
        private void clear() {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}