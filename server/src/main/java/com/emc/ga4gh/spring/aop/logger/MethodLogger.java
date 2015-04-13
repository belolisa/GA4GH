package com.emc.ga4gh.spring.aop.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class MethodLogger {

    static Logger logger = LoggerFactory.getLogger(MethodLogger.class);

    @Pointcut("execution(@com.emc.ga4gh.spring.aop.logger.Log * *.*(..))")
    public void LogPointcut() {
    }

    @Around("LogPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        Method method = MethodSignature.class.cast(point.getSignature()).getMethod();
        logger.info(
                String.format(
                        "%s#%s(%s): %s in %s millis",
                        method.getDeclaringClass(),
                        method.getName(),
                        Arrays.asList(point.getArgs()),
                        result,
                        System.currentTimeMillis() - start)
        );
        return result;
    }
}
