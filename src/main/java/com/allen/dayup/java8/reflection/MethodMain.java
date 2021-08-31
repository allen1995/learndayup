package com.allen.dayup.java8.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

/**
 * @Auther: allen
 * @Date: 2020-04-04 14:07
 * @Description:
 */
public class MethodMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        testStaticMethodReflect();
    }

    public static void testPrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Method method = Student.class.getMethod("getGrade", int.class);
        //设置getGrade方法权限为可访问
        method.setAccessible(true);
        method.invoke(student, 11);
    }

    public static void testStaticMethodReflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Integer.class.getMethod("parseInt", String.class);
        method.invoke(null, "2");
    }

    //反射调用方法
    public static void testMethodReflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //String s = "hello, World";
        //s.substring(6);

        String s = "hello, World";
        Method method = String.class.getMethod("substring", int.class);
        String s1 = (String)method.invoke(s, 6);
        System.out.println(s1);
    }

    private static void printMethod(Method method) {
        System.out.println("当前方法名称:" + method.getName());
        System.out.printf("[%s]方法所属类是:[%s]\n", method.getName(), method.getDeclaringClass());
        int modifier  = method.getModifiers();
        System.out.printf("[%s]方法权限类型是:[%s]\n", method.getName(), method.getModifiers());
        System.out.printf("[%s]方法是否是public方法:[%s]\n", method.getName(), Modifier.isPublic(modifier));
        System.out.printf("[%s]方法是否是final方法:[%s]\n", method.getName(), Modifier.isFinal(modifier));
        System.out.printf("[%s]方法是否是private方法:[%s]\n", method.getName(), Modifier.isPrivate(modifier));
        System.out.printf("[%s]方法是否是static方法:[%s]\n", method.getName(), Modifier.isStatic(modifier));
        System.out.printf("[%s]方法 类型参数是:[%s]\n", method.getName(), getTypeVariable(method.getTypeParameters()));
        System.out.printf("[%s]方法返回类型是:[%s]\n", method.getName(), method.getReturnType());
        System.out.printf("[%s]方法 参数类型是:[%s]\n", method.getName(), getClassNames(method.getParameterTypes()));
        System.out.printf("[%s]方法 参数个数是:[%s]\n", method.getName(), method.getParameterCount());
        System.out.printf("[%s]方法 泛型参数个数是:[%s]\n", method.getName(), method.getGenericParameterTypes()[0]);
    }

    private static String getClassNames(Class[] clazzs) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < clazzs.length; i++) {
            sb.append(clazzs[i].getSimpleName());
        }

        return sb.toString();
    }

    private static String getTypeVariable(TypeVariable[] typeVariables) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < typeVariables.length; i++) {
            sb.append(typeVariables[i].getName());
        }

        return sb.toString();
    }

    private static void test(Student student, String... arg){
        System.out.println(arg);
    }

    static class  Student<T> extends Person<T> {
        public int getScore(String type){
            return 19;
        }

        private int getGrade(int year){
            return 1;
        }
    }

    static class Person<T> {
        public int getScore(){
            return 11;
        }
    }
}
