package com.lance.test.spring.boot.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件条件
 *
 * @author Lance
 * @since 2021/1/6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnFileCondition.class)
public @interface ConditionalOnFile {

    /**
     * 文件名
     */
    String[] value();
}
