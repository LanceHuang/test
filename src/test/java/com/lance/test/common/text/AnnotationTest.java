package com.lance.test.common.text;

import org.junit.Test;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2017/4/22
 */
public class AnnotationTest {

    @Test
    public void test() {
        Class<Sub> clazz = Sub.class;

        System.out.println(Arrays.toString(clazz.getAnnotations()));
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
@interface A {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface B {
}

@A
class Super {

}

@B
class Sub extends Super {

}