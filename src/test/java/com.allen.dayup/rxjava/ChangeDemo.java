package com.allen.dayup.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.functions.Func2;

public class ChangeDemo {
    
    @Test
    public void test_map() {
        Observable.range(1,4)
                .map( i -> {
                    return i * i;
                })
                .subscribe( i -> {
                    System.out.println("map integer: " + i);
                });
    }
    
    @Test
    public void test_flapMap() {
        Observable.range(1, 4)
                .flatMap( i -> Observable.just( i * i, i * i + 1))
                .subscribe( i -> {
                    System.out.println("flapMap integer: " + i );
                });
    }
    
    @Test
    public void test_flapMap2() {
        Observable.range(1,4)
                .flatMap( i -> Observable.range(1, i).toList())
                .subscribe(list -> {
                    System.out.println("list: " + list.toString());
                });
    }
    
    @Test
    public void test_scan() {
        // 定义一个累加函数
        Func2<Integer, Integer, Integer> accumlator = new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                System.out.println(integer + " + " + integer2 + " = " + (integer+integer2));
                return integer + integer2;
            }
        };
        
        Observable.range(1,5) 
                .scan(accumlator)
                .subscribe( sum -> {
                    System.out.println("sum: " + sum );
                });
    }
}
