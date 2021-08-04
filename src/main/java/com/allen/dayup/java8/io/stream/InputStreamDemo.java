package com.allen.dayup.java8.io.stream;

import java.io.*;
import java.nio.file.Paths;

/**
 * @Auther: 20190598
 * @Date: 2021/8/4 16:20
 * @Description:
 */
public class InputStreamDemo {

    public static void main(String[] args) {
        testBufferedInputStram();
    }

    public static void testFileInputStream() {
        try (FileInputStream is = new FileInputStream(Paths.get("E:/test1/test.txt").toFile())){
            int n;

            while ( (n = is.read()) != -1  ) {
                System.out.print((char)n);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileInputStreamBuffer() {
        try (FileInputStream is = new FileInputStream(Paths.get("E:/test1/test.txt").toFile())){
            int n;

            byte[] buffer = new byte[1000];
            while ( (n = is.read(buffer)) != -1  ) {
                System.out.println(new String(buffer,0,n));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testInputStreamStringBuilder() {
        try (FileInputStream is = new FileInputStream(Paths.get("E:/test1/test.txt").toFile())){
            int n;

            StringBuilder sb = new StringBuilder();
            while ( (n = is.read()) != -1  ) {
                sb.append((char) n);
            }

            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testByteArrayInputStream() {
        byte[] bytes = {97, 108, 108, 101, 110, 46};

        try (InputStream is = new ByteArrayInputStream(bytes)) {
            int n;

            StringBuilder sb = new StringBuilder();
            while( (n = is.read()) != -1 ) {
                sb.append((char)n);
            }

            System.out.println(sb.toString());

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void testBufferedInputStram() {
        try (InputStream is = new BufferedInputStream(new FileInputStream(Paths.get("E:/test1/test.txt").toFile()))){
            int n;

            StringBuilder sb = new StringBuilder();
            while ( (n = is.read()) != -1  ) {
                sb.append((char) n);
            }

            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
