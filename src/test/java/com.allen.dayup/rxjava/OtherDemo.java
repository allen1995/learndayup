package com.allen.dayup.rxjava;

import org.junit.Test;
import rx.Observable;

public class OtherDemo {
    
    @Test
    public void test_take() {
        Observable.range(1, 5)
                .take(2)
                .subscribe( integer -> {
                    System.out.println("i: " + integer);
                });
    }
    
    @Test
    public void test_window() {
        Observable.range(1, 10)
                .window(2)
                .flatMap( o -> o.toList())
                .subscribe( list -> {
                    System.out.println("list: " + list.toString());
                });
    }
    
    @Test
    public void test_window2() {
        Observable.range(1, 10)
                .window(3, 1)
                .flatMap( o -> o.toList())
                .subscribe( list -> {
                    System.out.println("list: " + list.toString());
                });
    }
}
