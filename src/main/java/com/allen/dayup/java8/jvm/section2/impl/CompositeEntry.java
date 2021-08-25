package com.allen.dayup.java8.jvm.section2.impl;

import com.allen.dayup.java8.jvm.section2.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: allen
 * @Date: 2021-07-26 21:34
 * @Description:
 */
public class CompositeEntry implements Entry {

    private final List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(File.pathSeparator);
        for ( String path : paths ) {
            entryList.add(Entry.create(path));
        }
    }


    @Override
    public byte[] readClass(String className) throws IOException {
        for( Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (IOException e) {
                //ignore
            }
        }

        throw new IOException("class not found." + className);
    }

    @Override
    public String toString() {
        String[] strs = new String[entryList.size()];

        for( int i = 0; i < entryList.size(); i++) {
            strs[i] = entryList.get(i).toString();
        }

        return String.join(File.pathSeparator, strs);
    }
}
