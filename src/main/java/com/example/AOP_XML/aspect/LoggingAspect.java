package com.example.AOP_XML.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;


public class LoggingAspect {
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Before Method:  " + joinPoint.getSignature().getName());
    }

    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("Before Execution:  " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();


        long end = System.currentTimeMillis();
        System.out.println("After Execution: " + joinPoint.getSignature().getName());
        System.out.println("Execution Time : " + (end-start) + "ms");

        return result;
    }
}
