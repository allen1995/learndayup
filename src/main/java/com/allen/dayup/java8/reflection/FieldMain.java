package com.allen.dayup.java8.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

/**
 * @Auther: allen
 * @Date: 2020-04-04 12:38
 * @Description:
 */
public class FieldMain {

    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;

        //获取public字段
        System.out.println(stdClass.getField("score"));

        //获取父类的public字段
        System.out.println(stdClass.getField("name"));

        //获取private字段grade
        System.out.println(stdClass.getDeclaredField("grade"));

        //打印stdClass.getFields
        printFields(stdClass.getFields());

        System.out.println();

        //打印stdClass.getDeclareFields
        printFields(stdClass.getDeclaredFields());
        System.out.println();

        //获取字段信息
        getFieldInfo(String.class.getDeclaredField("value"));
        getFieldInfo(Car.class.getDeclaredField("age"));

        //获取字段的值
        Person p = new Person();
        p.name = "allen";
        Class pCla = p.getClass();
        Field f = pCla.getDeclaredField("name");
        System.out.println("name字段的值：" + f.get(p));

        //设置私有字段的值
        Student student = new Student();
        Field gradeField = Student.class.getDeclaredField("grade");
        gradeField.setAccessible(true);
        gradeField.set(student, 11);
        System.out.println("student的grade值:" + student.getGrade());

    }

    private static void printFields(Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            System.out.print(fields[i] + ",");
        }
    }

    private static void getFieldInfo(Field field) throws Exception{
        System.out.println("Field name:" + field.getName());
        System.out.println("Field type:" + field.getType());
        System.out.println("Field generic type:" + field.getGenericType());
        int m = field.getModifiers();
        System.out.println(Modifier.isFinal(m));
        System.out.println(Modifier.isPublic(m));
        System.out.println(Modifier.isStatic(m));


    }

    static class Car<T> {
        public String name;

        private T age;
    }

    static class Student extends Person {
        public int score;
        private int grade;

        public int getGrade() {
            return grade;
        }
    }

    static class Person {
        public String name;

    }
}
