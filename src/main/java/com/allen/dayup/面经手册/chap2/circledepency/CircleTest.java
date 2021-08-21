package com.allen.dayup.面经手册.chap2.circledepency;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 20190598
 * @Date: 2021/7/30 14:08
 * @Description:
 */
public class CircleTest {
    private static final Map<String,Object> singletonObjects = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println(getBean(A.class).getB());
        System.out.println(getBean(B.class).getA());
    }

    private static  <T> T getBean(Class<T> beanClass) throws Exception{
        String beanName = beanClass.getSimpleName().toLowerCase();
        if( singletonObjects.containsKey(beanName)) {
            return (T) singletonObjects.get(beanName);
        }

        //实例化对象放入缓存
        Object obj = beanClass.newInstance();
        singletonObjects.put(beanName, obj);

        //属性填充
        Field[] fields = obj.getClass().getDeclaredFields();

        for ( Field field : fields ) {
            field.setAccessible(true);
            Class<?> fieldClass = field.getType();
            String fieldBeanname = fieldClass.getSimpleName().toLowerCase();
            field.set(obj, singletonObjects.containsKey(fieldBeanname) ? singletonObjects.get(fieldBeanname) : getBean(fieldClass));
            field.setAccessible(false);
        }

        return (T)obj;
    }

}


class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}