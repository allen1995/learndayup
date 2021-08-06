package com.allen.dayup.java8.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @Auther: 20190598
 * @Date: 2021/8/5 09:50
 * @Description:
 */
public class PrintStreamDemo {

    public static void main(String[] args) {
        testPrintWriter();
    }

    public static void testPrintStream() {
        try (PrintStream printStream = new PrintStream(new FileOutputStream("E:/test1/test2.txt"))){
            printStream.println("test1");
            printStream.println("i am test2.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testPrintWriter() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("E:/test1/test2.txt"))){
            writer.println("PrintWriter test.");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
