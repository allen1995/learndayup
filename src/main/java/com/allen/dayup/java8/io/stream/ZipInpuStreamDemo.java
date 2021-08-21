package com.allen.dayup.java8.io.stream;


import java.io.*;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: 20190598
 * @Date: 2021/8/4 19:29
 * @Description:
 */
public class ZipInpuStreamDemo {

    public static void main(String[] args) {
        testZipOutputStream();
    }

    public static void testZipInpuStream() {
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream("E:/test1/test2.zip"))) {
            ZipEntry zipEntry;

            while ( (zipEntry = zis.getNextEntry()) != null ) {
                String name = zipEntry.getName();

                if( !zipEntry.isDirectory() ) {
                    int n;
                    StringBuilder sb = new StringBuilder();

                    while( (n = zis.read()) != -1 ) {
                        sb.append((char) n);
                    }

                    System.out.println(sb.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testZipOutputStream() {
        try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("E:/test1/test3.zip"))) {
            File file = new File("test.txt");

            zip.putNextEntry(new ZipEntry(file.getName()));
            zip.write("Hello,World".getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
