package com.allen.dayup.java8.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Auther: allen
 * @Date: 2020-04-04 14:07
 * @Description:
 */
public class MethodMain {

    public static void main(String[] args) throws NoSuchMethodException {
        Class stdClass = Student.class;
        Method scoreMethod = stdClass.getMethod("getScore",String.class);
        printMethod(scoreMethod);
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
        System.out.printf("[%s]方法 类型参数是:[%s]\n", method.getName(), method.getTypeParameters());
        System.out.printf("[%s]方法返回类型是:[%s]\n", method.getName(), method.getReturnType());
        System.out.printf("[%s]方法 参数类型是:[%s]\n", method.getName(), getClassNames(method.getParameterTypes()));
        System.out.printf("[%s]方法 参数个数是:[%s]\n", method.getName(), method.getParameterCount());
        System.out.printf("[%s]方法 泛型参数个数是:[%s]\n", method.getName(), method.getGenericParameterTypes());
    }

    private static String getClassNames(Class[] clazzs) {
        StringBuffer sb = new StringBuffer();

        sb.append("[");
        for (int i = 0; i < clazzs.length; i++) {
            sb.append(clazzs[i].getSimpleName());
        }
        sb.append("]");

        return sb.toString();
    }

    private static void test(Student student, String... arg){
        System.out.println(arg);
    }

    static class Student extends Person {
        public int getScore(String type){
            return 19;
        }

        private int getGrade(int year){
            return 1;
        }
    }

    static class Person {
        public String getName(){
            return "Person";
        }
    }
}
