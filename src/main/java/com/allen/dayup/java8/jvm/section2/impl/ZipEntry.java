package com.allen.dayup.java8.jvm.section2.impl;

import com.allen.dayup.java8.jvm.section2.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Auther: allen
 * @Date: 2021-07-26 21:37
 * @Description:
 */
public class ZipEntry implements Entry {

    private Path absolutePath;

    public ZipEntry(String path) {

        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        try(FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
