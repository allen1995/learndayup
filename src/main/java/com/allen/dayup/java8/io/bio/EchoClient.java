package com.allen.dayup.java8.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: 20190598
 * @Date: 2022/11/18 17:27
 * @Description:
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8081);

        OutputStream outputStream = null;

        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write("hello, world".getBytes());
            outputStream.flush();

            //inputStream = socket.getInputStream();
            //reader = new BufferedReader(new InputStreamReader(inputStream));
            //
            //System.out.println("resp:" + reader.readLine());
        } finally {
            if( outputStream != null ) {
                outputStream.close();
            }

            if( inputStream != null ) {
                inputStream.close();
            }

            if( reader != null ) {
                reader.close();
            }

            if( socket != null ) {
                socket.close();
            }
        }

    }
}
