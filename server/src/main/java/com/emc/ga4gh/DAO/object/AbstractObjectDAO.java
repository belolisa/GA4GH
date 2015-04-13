package com.emc.ga4gh.DAO.object;

import com.emc.ga4gh.DAO.CrudDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DTO.Entity;
import com.emc.ga4gh.spring.aop.logger.Log;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Elizaveta Belokopytova.
 */

@OTransacrional
public abstract class AbstractObjectDAO<T extends Entity> implements CrudDAO<T> {

    @Autowired
    protected OObjectDatabaseTx db;

    @Override
    @Log
    public T create(T newInstance) {
        return db.save(newInstance);
    }

    @Override
    @Log
    public T read(String id) {
        return null;
    }

    @Override
    @Log
    public void update(T transientObject) {

    }

    @Override
    @Log
    public void delete(T persistentObject) {

    }
}
