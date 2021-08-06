package com.allen.dayup.java8.io;

import java.io.*;

/**
 * @Auther: 20190598
 * @Date: 2021/8/5 09:16
 * @Description:
 */
public class ReaderDemo {

    public static void main(String[] args) {
        testFileReader();
    }

    public static void testBufferedReader() {
        try(BufferedReader reader = new BufferedReader(new FileReader("E:/test1/test.txt"))) {
            String line;
            while ( ( line = reader.readLine()) != null ) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileReader() {
        try(FileReader reader = new FileReader("E:/test1/test.txt")) {
            int n;
            while ( ( n = reader.read()) != -1 ) {
                System.out.print((char)n);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
