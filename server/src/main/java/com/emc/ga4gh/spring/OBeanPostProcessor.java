package com.emc.ga4gh.spring;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Elizaveta Belokopytova.
 */

public class OBeanPostProcessor implements BeanPostProcessor {

    @Value("${o.password}")
    private String password;

    @Value("${o.user}")
    private String user;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        System.out.println("s = " + s);

        if (o instanceof OObjectDatabaseTx) {
            OObjectDatabaseTx db = ((OObjectDatabaseTx) o).open(user, password);
            db.getEntityManager().registerEntityClasses("com.emc.ga4gh.DTO");
            System.out.println("db opened");
            return db;
        }
        System.out.println("bean post processor invoked");
        return o;
    }
}
