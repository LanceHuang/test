package com.lance.test.common.text;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2017/4/22
 */
public class AnnotationTest {

    @Test
    public void test() {
        Class<B> clazz = B.class;

        System.out.println(Arrays.toString(clazz.getAnnotations()));
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));

        System.out.println(Arrays.toString(Super.class.getAnnotations()));
        System.out.println(Arrays.toString(Sub.class.getAnnotations()));

        System.out.println(Super.class.isAnnotationPresent(A.class));
        System.out.println(Sub.class.isAnnotationPresent(C.class));
        System.out.println(Sub.class.isAnnotationPresent(A.class));
    }


    @Test
    public void testInherit() {
        System.out.println(C.class.isAnnotationPresent(A.class));
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface A {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface B {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@A
@interface C {

}

@A
class Super {

}

@B
@C
class Sub extends Super {

}