package com.lance.test.aspect.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Not work, because it didn't mark with @Component
 */
@Aspect
public class TraceAspect {

    @Before("execution(* com.lance.test.aspect.service..*.*(..))")
    public void trace() {
        System.out.println("Hello, this is trace aspect");
    }
}
