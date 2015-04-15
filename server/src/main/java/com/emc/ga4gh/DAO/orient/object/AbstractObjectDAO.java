package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.CrudDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DTO.Entity;
import com.emc.ga4gh.spring.aop.logger.Log;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public Optional<T> read(String rid) {
        List<?> query = db.query(new OSQLSynchQuery<T>("select from " + getEntityName() + " where @rid = " + rid + " limit 1"));
        if (query.size() > 0) {
            return Optional.ofNullable((T) query.get(0));
        } else return Optional.empty();
    }

    @Override
    @Log
    public void update(T transientObject) {
        db.save(transientObject);
    }

    @Override
    @Log
    public void delete(T persistentObject) {
        db.delete(persistentObject);
    }

    protected abstract String getEntityName();
}
