package com.emc.ga4gh.spring;

import com.orientechnologies.orient.core.tx.OTransaction;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
public class DAOMethodInterceptor implements MethodInterceptor {

    @Autowired
    OObjectDatabaseTx db;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            db.begin(OTransaction.TXTYPE.OPTIMISTIC);
            return invocation.proceed();
        } catch (Exception e) {
            db.rollback();
            throw e;
        } finally {
            db.commit();
        }
    }
}
