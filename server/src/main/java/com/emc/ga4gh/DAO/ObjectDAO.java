package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.OEntity;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Elizaveta Belokopytova.
 */

@Repository
public class ObjectDAO<T extends OEntity> implements CrudDAO<T, String> {

    @Autowired
    OObjectDatabaseTx db;

    @Override
    public String create(T newInstance) {
        return db.save(newInstance);
    }

    @Override
    public T read(String id) {
        return null;
    }

    @Override
    public void update(T transientObject) {

    }

    @Override
    public void delete(T persistentObject) {

    }
}
