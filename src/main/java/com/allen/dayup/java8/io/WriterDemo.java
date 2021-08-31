package com.allen.dayup.java8.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: 20190598
 * @Date: 2021/8/5 09:46
 * @Description:
 */
public class WriterDemo {

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("E:/test1/test2.txt"))) {

            writer.write("abc");
            writer.newLine();
            writer.write("i am allen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
