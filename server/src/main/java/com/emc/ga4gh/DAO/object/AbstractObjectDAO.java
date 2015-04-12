package com.emc.ga4gh.DAO.object;

import com.emc.ga4gh.DAO.CrudDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DTO.OEntity;
import com.emc.ga4gh.spring.aop.logger.Loggable;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Elizaveta Belokopytova.
 */

@OTransacrional
public abstract class AbstractObjectDAO<T extends OEntity> implements CrudDAO<T> {

    @Autowired
    protected OObjectDatabaseTx db;

    @Override
    @Loggable
    public String create(T newInstance) {
        T entity = db.save(newInstance);
        return entity.toString();
    }

    @Override
    @Loggable
    public T read(String id) {
        return null;
    }

    @Override
    @Loggable
    public void update(T transientObject) {

    }

    @Override
    @Loggable
    public void delete(T persistentObject) {

    }
}
