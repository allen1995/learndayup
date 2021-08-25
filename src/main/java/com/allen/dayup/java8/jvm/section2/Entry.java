package com.allen.dayup.java8.jvm.section2;

import com.allen.dayup.java8.jvm.section2.impl.CompositeEntry;
import com.allen.dayup.java8.jvm.section2.impl.DirEntry;
import com.allen.dayup.java8.jvm.section2.impl.WildcardEntry;
import com.allen.dayup.java8.jvm.section2.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: allen
 * @Date: 2021-07-26 21:32
 * @Description:
 */
public interface Entry {

    byte[] readClass(String className) throws IOException;

    static Entry create(String path) {
        if( path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if(path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if( path.endsWith(".jar") || path.endsWith(".JAR")
         || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
