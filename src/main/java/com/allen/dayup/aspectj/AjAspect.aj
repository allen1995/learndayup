package com.allen.dayup.aspectj;

public aspect AjAspect {
    pointcut say():
            execution(* com.allen..dayup.aspectj.App.say(..));
    before(): say() {
        System.out.println("Ajaspect before say");
    }

    after(): say() {
        System.out.println("Ajaspect after say");
    }
}