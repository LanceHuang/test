package com.lance.test.common.lang;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

/**
 * @author Lance
 * @since 2020/12/28
 */
@RepeatableAnnotation("annotation1")
@RepeatableAnnotation("annotation2")
public class RepeatableTest {

    @Test
    public void test() {
        Annotation[] declaredAnnotations = RepeatableTest.class.getDeclaredAnnotations();
        Assert.assertEquals(declaredAnnotations.length, 1);
        Assert.assertTrue(declaredAnnotations[0] instanceof RepeatableAnnotations);
    }
}
