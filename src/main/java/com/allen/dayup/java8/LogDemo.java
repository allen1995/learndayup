package com.allen.dayup.java8;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.logging.Logger;


/**
 * @Auther: 20190598
 * @Date: 2021/8/11 19:45
 * @Description:
 */
public class LogDemo {



    public static void main(String[] args) {
        test_commonlog();


    }

    private static void test_jdklog() {
        Logger LOG = Logger.getGlobal();

        LOG.info("start procses");

        LOG.warning("memory is running out.");
        LOG.fine("ignore");
        LOG.severe("program will be terminated.");
    }

    private static void test_commonlog() {
        Log log  = LogFactory.getLog(LogDemo.class);

        log.info("start procses");
        log.warn("program will be terminated.");

    }
}
