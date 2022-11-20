package com.allen.dayup.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.functions.Func2;

public class AggregationDemo {
    
    @Test
    public void test_count() {
        Integer sum = Observable.range(1, 5) 
                .count()
                .toBlocking().single();

        System.out.println("sum: " + sum);
    }
    
    @Test
    public void test_reduce() {
        Func2<Integer, Integer, Integer> accumulator = new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                System.out.println(integer + " + " + integer2 + " = " + (integer+integer2));
                return integer + integer2;
            }
        };
        
        Observable.range(1, 5)
                .reduce(accumulator)
                .subscribe( integer -> {
                    System.out.println("reduce inteter: " + integer);
                });
    } 
}
