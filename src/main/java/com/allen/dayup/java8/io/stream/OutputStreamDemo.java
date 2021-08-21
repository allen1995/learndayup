package com.allen.dayup.java8.io.stream;

import java.io.*;
import java.nio.file.Paths;

/**
 * @Auther: 20190598
 * @Date: 2021/8/4 18:44
 * @Description:
 */
public class OutputStreamDemo {

    public static void main(String[] args) {
        testByteArrayOutputStream();
    }

    public static void testFileOutputStream() {
        try (OutputStream os = new FileOutputStream(Paths.get("E:/test1/test2.txt").toFile())) {

            os.write("hello,World".getBytes("UTF-8"));
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testByteArrayOutputStream() {
        byte[] data;

        try (OutputStream os = new ByteArrayOutputStream()) {

            os.write("hello,World1".getBytes("UTF-8"));
            os.flush();

            data = ((ByteArrayOutputStream) os).toByteArray();

            System.out.println(new String(data, "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
