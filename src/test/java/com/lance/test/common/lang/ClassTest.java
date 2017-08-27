package com.lance.test.common.lang;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ClassTest {

    @Test
    public void testGetClass() {
        System.out.println(this.getClass());
        System.out.println(ClassTest.class);
        System.out.println(ClassTest.class.equals(this.getClass()));
        System.out.println(ClassTest.class == this.getClass());
    }

    @Test
    public void testAnnotation() {
        try {
            Class cls = Class.forName("java.lang.Override");
            Annotation[] annotations = cls.getAnnotations();

            int len = annotations.length;
            System.out.println("注解数：" + len);

            for (int i = 0; i < len; i++) {
                System.out.println(annotations[i]);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定类");
        }
    }

    @Test
    public void testMethod() {
        Class cls = this.getClass();
        Method[] methods = cls.getDeclaredMethods();

        int len = methods.length;
        System.out.println("方法数：" + len);

        for (int i = 0; i < len; i++) {
            System.out.println(methods[i]);

            Annotation[] annotations = methods[i].getAnnotations();
            int size = annotations.length;
            System.out.println("注解数：" + size);
            for (int j = 0; j < size; j++) {
                System.out.println("\t" + annotations[j]);
            }
        }
    }

    public void sop() {
        System.out.println("Hello Class");
    }

    @Test
    public void testClassFile() {
        String filename = "";
    }

}
