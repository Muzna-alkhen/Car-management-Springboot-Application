package com.example.WepApplications.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(com.example.WepApplications.aspect.Log)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("***LOG***\n"+joinPoint.getSignature().getName()+ " executed in " + executionTime + "ms "+" By "+auth.getName());
        return proceed;
    }
}
