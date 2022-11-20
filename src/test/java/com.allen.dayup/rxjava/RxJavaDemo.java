package com.allen.dayup.rxjava;


import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;

public class RxJavaDemo {
    
    @Test
    public void rxjavaBaseUse() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        } ).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }
    
    @Test
    public void test_defer() {
        Integer[] arr = new Integer[] {1,2,3,4,5};
        
        
        Observable<Integer> observable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.from(arr);
            }
        });
        
//        arr = new Integer[] {16,76,89};
                
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer:[" + integer + "]");
            }
        });
    }
}
