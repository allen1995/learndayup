package com.allen.dayup.thinkinjava.chap15;

/**
 * @Auther: 20190598
 * @Date: 2019/11/18 11:06
 * @Description:
 */
public class GenericCast {

    private static final int SIZE = 10;

    public static void main(String[] args){

        FixedSizeStack<String> strings = new FixedSizeStack<>(SIZE);

        for ( String s : "A B C D E F G H I J".split(" ")){
            strings.push(s);
        }

        for (int i = 0; i < SIZE; i++) {
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}

class FixedSizeStack<T> {

    private int index = 0;
    private Object[] storage;

    public FixedSizeStack(int size) {
        this.storage = new Object[size];
    }

    public void push(T item){
        storage[index++] = item;
    }

    public T pop(){
        return (T) storage[--index];
    }
}
