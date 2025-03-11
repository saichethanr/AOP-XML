package com.example.AOP_XML.aspect;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("spring-aop-tracing");

    public Object traceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // Retrieve the current span (if exists) for context propagation
        Span parentSpan = Span.current();

        // Create a new child span
        Span span = tracer.spanBuilder(methodName)
                .setParent(Context.current())  // Propagate parent span
                .startSpan();

        try (Scope scope = span.makeCurrent()) { // Ensure context propagation
            return joinPoint.proceed(); // Execute the actual method
        } catch (Throwable t) {
            span.recordException(t);
            span.setStatus(StatusCode.ERROR);
            throw t;
        } finally {
            span.end(); // End the span
        }
    }
}
