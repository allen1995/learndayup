package com.allen.dayup.rxjava;

import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.functions.Action1;

/**
 * @Auther: 20190598
 * @Date: 2022/11/2 09:12
 * @Description:
 */
public class RxJavaDemo {

    @Test
    public void test_firstRxJava() {
        String[] names = {"allen", "Bob"};

        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello," + s);
            }
        });
    }

}
