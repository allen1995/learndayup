package com.allen.dayup.rxjava;

import com.allen.dayup.thinkinjava.A;
import org.junit.Test;
import rx.Emitter;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BackpressureDemo {
    @Test
    public void test_backpressure() {
        // 主题实例，使用背压
        Observable observable = Observable.create(
                new Action1<Emitter<String>>() {
                    @Override
                    public void call(Emitter<String> stringEmitter) {
                        for (int i = 0; ; i++) {
                            stringEmitter.onNext(String.valueOf(i));
                        }
                    }
                }, Emitter.BackpressureMode.LATEST
        );

        // 订阅者
        Action1<String> subscriber = new Action1<String>() {
            @Override
            public void call(String s) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("consumer: " + s);
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
}
