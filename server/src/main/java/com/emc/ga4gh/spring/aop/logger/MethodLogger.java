package com.emc.ga4gh.spring.aop.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liza on 12.04.15.
 */

@Aspect
public class MethodLogger {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(@Loggable * com.emc.ga4gh.*.*(..))")
    public void LogPointcut(){}


    @Around("LogPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        System.out.println("HELLO");
        logger.debug(
                "#%s(%s): %s in %[msec]s",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }
}
