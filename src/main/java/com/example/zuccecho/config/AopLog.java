package com.example.zuccecho.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class AopLog {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.example.zuccecho..*.*(..))")
    public void aopWebLog(){
        System.out.println("before.....");
    }

    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint){

    }
}
