package com.lance.test.hibernatevalidator;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具，可以校验对象下的字段
 *
 * @author Lance
 * @since 2022/3/31
 */
public class HibernateValidatorTest {

    @Test
    public void test() {
        TestBean testBean = new TestBean();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TestBean>> results = validator.validate(testBean);
        results.forEach(result -> System.out.println(format(result)));
    }

    private String format(ConstraintViolation<?> violation) {
        String className = violation.getRootBeanClass().getSimpleName();
        String propertyName = violation.getPropertyPath().toString();
        String message = violation.getMessage();
        return className + "." + propertyName + "=" + violation.getInvalidValue() + " " + message;
    }
}
