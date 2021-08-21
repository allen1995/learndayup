package com.allen.dayup.java8.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * @Auther: 20190598
 * @Date: 2019/12/24 11:01
 * @Description:
 */
public class PathDemo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./test2/test.txt");

        //
        System.out.println(path.getFileSystem());

        System.out.println(path.isAbsolute()); // true 判断路径是否为绝对路径

        System.out.println(path.getRoot()); // E:\

        System.out.println(path.getFileName()); // test.txt

        System.out.println(path.getParent()); // 获取上层目录

        System.out.println(path.getNameCount()); // 统计路径深度

        System.out.println(path.getName(0)); // test1, 父文件一级路径

        System.out.println(path.subpath(0,1)); // E:\test1\test.txt ,(0,1) -> E:\test1 | E:\test1\test.txt,(1,2) -> test.txt

        System.out.println(path.startsWith("E:/test1")); // true

        System.out.println(path.endsWith(Paths.get("test.txt"))); // true

        // a: E:/test1/test.txt, b: E:/test2 -> E:/test2  | E:/test1/test.txt, test2.txt. E:/test1/test.txt/test2.txt
        System.out.println(path.resolve(Paths.get("./test1/test"))); //解析路径


    }

    public static void iterateDir() throws IOException {
        Path path = Paths.get("E:/");
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for( Path e : stream ){
            System.out.println(e.getFileName());
        }
    }

    public static void fileRead() throws IOException {
        Path path = Paths.get("E:/test1.txt");
        Path path1 = FileSystems.getDefault().getPath("E:/", "text1.txt");

        if( !Files.exists(path)){
            Files.createFile(path);
        }

        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        String line = "";
        while ( (line = reader.readLine()) != null ){
            System.out.println(line);
        }
    }
}
