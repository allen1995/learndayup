package com.allen.dayup.rxjava;

import com.allen.dayup.thinkinjava.A;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RxJava创建型操作测试类
 */
public class RxjavaCreateDemo {

    @Test
    public void test_from() {
        Integer[] items = {0, 1, 2, 3, 4, 5};

        Observable<Integer> observable = Observable.from(items);

        observable.subscribe(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer item) {
                        System.out.println(item);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Error encountered:" + throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("sequence complete.");
                    }
                }
        );
    }

    /**
     *
     */
    @Test
    public void test_interval() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(aLong);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Error encountered:" + throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Sequence complete.");
                    }
                }
        );
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void test_just() {
        // 弹射一个字符串
        Observable.just("hello world")
                .subscribe(s -> {
                    System.out.println("s:" + s);
                });

        // 逐一发送1，2，3，4
        Observable.just(1, 2, 3, 4)
                .subscribe(i -> {
                    System.out.println("i:" + i);
                });
    }
    
    @Test
    public void test_defer() {
        AtomicInteger foo = new AtomicInteger(100);
        Observable observable = Observable.just(foo.get());

        Observable dObservable = Observable.defer(() -> {
            return Observable.just(foo.get());
        });

        foo.set(200);

        observable.subscribe(i -> {
            System.out.println("i:" + i);
        });

        dObservable.subscribe(i -> {
            System.out.println("di:" + i);
        });
        
    }
}
