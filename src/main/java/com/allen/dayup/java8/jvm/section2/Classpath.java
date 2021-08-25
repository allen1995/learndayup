package com.allen.dayup.java8.jvm.section2;

import com.allen.dayup.java8.jvm.section2.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Auther: allen
 * @Date: 2021-07-27 21:27
 * @Description:
 */
public class Classpath {
    //启动类路径
    private Entry bootstrapClasspath;

    //扩展类路径
    private Entry extensionClasspath;

    //用户类路径
    private Entry userClasspath;

    public Classpath(String jreOption, String cpOption) {
        //启动类&扩展类 "C:\Program Files\Java\jdk1.8.0_161\jre"
        bootstrapAndExtensionClasspath(jreOption);

        //用户类 F:\..\org\itstack\demo\test\HelloWorld
        parseUserClasspath(cpOption);
    }

    private void parseUserClasspath(String cpOption) {

        if(cpOption == null ) {
            cpOption = ".";
        }

        userClasspath = Entry.create(cpOption);
    }

    private void bootstrapAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);

        // ../jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        // ../jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);


    }

    private String getJreDir(String jreOption) {
        if( jreOption != null && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }

        if( Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }

        String jh = System.getenv("JAVA_HOME");

        if( jh != null ) {
            return Paths.get(jh, "jre").toString();
        }

        throw new RuntimeException("Can not find JRE floder.");

    }

    public byte[] readClass(String className) throws Exception {
        className = className + ".class";

        //[readClass]启动类路径
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        //[readClass]扩展类路径
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            //ignored
        }

        //[readClass]用户类路径
        return userClasspath.readClass(className);
    }
}
