package com.emc.ga4gh.spring;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
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
        if (o instanceof OObjectDatabaseTx) {
            OObjectDatabaseTx db = ((OObjectDatabaseTx) o).open(user, password);
            db.getEntityManager().registerEntityClasses("com.emc.ga4gh.DTO");
            return db;
        }
        return o;
    }
}
