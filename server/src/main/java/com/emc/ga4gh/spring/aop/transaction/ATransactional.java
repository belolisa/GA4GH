package com.emc.ga4gh.spring.aop.transaction;

import com.emc.ga4gh.DAO.OTransacrional;
import com.orientechnologies.orient.core.tx.OTransaction;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liza on 12.04.15.
 */


@Component
@Aspect
public class ATransactional {

    @Autowired
    OObjectDatabaseTx db;

    @Pointcut("execution(@com.emc.ga4gh.DAO.OTransacrional * com.emc.ga4gh.DAO.impl.*.*(..))")
    public void DAOPointcut(){}

    @Around("DAOPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            db.begin(OTransaction.TXTYPE.OPTIMISTIC);
            System.out.println("TRANSAAA!");
            return point.proceed();
        } catch (Exception e) {
            db.rollback();
            throw e;
        } finally {
            db.commit();
        }
    }
}

