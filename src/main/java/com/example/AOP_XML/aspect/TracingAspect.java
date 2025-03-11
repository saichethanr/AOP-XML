package com.example.AOP_XML.aspect;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("spring-aop-tracing");

    @Around("execution(* com.example.AOP_XML..*(..))")
    public Object tracemethod(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().toShortString();
        Span span = tracer.spanBuilder(methodName).startSpan();

        try{
            return joinPoint.proceed();
        }
        catch (Throwable t){
            span.recordException(t);
            throw t;
        }
        finally {
            span.end();
        }
    }
}
