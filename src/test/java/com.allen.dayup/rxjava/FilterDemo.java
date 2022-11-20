package com.allen.dayup.rxjava;

import org.junit.Test;
import rx.Observable;

public class FilterDemo {

    @Test
    public void test_Fileter() {
        Observable.range(1, 20)
                .filter(i -> {
                    return i % 5 == 0;
                })
                .subscribe(i -> {
                    System.out.println("filter int: " + i);
                });
    }
}
