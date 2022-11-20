package com.allen.dayup.rxjava;

import org.junit.Test;
import rx.Observable;

public class DinstinctDemo {
    
    @Test
    public void test_distinct() {
        Observable.just("allen", "azhe", "allen", "aou")
                .distinct()
                .subscribe( s -> {
                    System.out.println("s:" + s);
                });
    }
}
