package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.AbstractSQLBuilder;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DAO.CrudSelectDAO;
import com.emc.ga4gh.DAO.SelectBuilder;
import com.emc.ga4gh.DTO.Entity;
import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.spring.aop.logger.Log;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Elizaveta Belokopytova.
 */

@OTransacrional
public abstract class AbstractObjectDAO<T extends Entity> implements CrudSelectDAO<T> {

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

        SelectBuilder selectBuilder = (SelectBuilder) getSelectBuilder()
                .setWhat("")
                .setObjectParameter("@rid", rid)
                .setQueryParameter("limit", "1");

        List<T> result = querySelect(selectBuilder);
        if (result.size() > 0) {
            return Optional.ofNullable(result.get(0));
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

    private SelectBuilder getSelectBuilder() {
        return new SelectBuilder(getEntityName());
    }

    private List<T> querySelect(SelectBuilder builder) {
        return db.query(new OSQLSynchQuery<T>(builder.build()));
    }

    protected abstract String getEntityName();
}
