package com.emc.ga4gh.spring.aop.transaction;

import com.orientechnologies.orient.core.tx.OTransaction;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by liza on 12.04.15.
 */


@Component
@Aspect
public class AspectTransactional {

    private Logger logger = LoggerFactory.getLogger(AspectTransactional.class);

    @Autowired
    OObjectDatabaseTx db;

    @Pointcut("within(@com.emc.ga4gh.DAO.OTransacrional *) || execution(@com.emc.ga4gh.DAO.OTransacrional * *.*(..))")
    public void DAOPointcut(){}

    @Around("DAOPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
//            db.begin(OTransaction.TXTYPE.OPTIMISTIC);
            db.begin();
            Method method = MethodSignature.class.cast(point.getSignature()).getMethod();
            logger.info(
                    String.format(
                            "%s#%s(%s): is transactional",
                            method.getDeclaringClass(),
                            method.getName(),
                            Arrays.asList(point.getArgs())
                    )
            );
            return point.proceed();
        } catch (Exception e) {
            db.rollback();
            throw e;
        } finally {
            db.commit();
        }
    }
}

