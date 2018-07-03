package com.lance.test.aspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfileAspect {

    @Around(value = "execution(* com.lance.test.aspect.service.impl.*.*(int)) && args(id)", argNames = "joinPoint,id")
    public Object profile(ProceedingJoinPoint joinPoint, int id) throws Throwable {
        long start = System.currentTimeMillis();
        Object resultObj = joinPoint.proceed();
        long end = System.currentTimeMillis();

        System.out.println("Profile: " + (end - start));
        return resultObj;
    }

}
