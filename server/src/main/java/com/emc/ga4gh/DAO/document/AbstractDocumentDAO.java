package com.emc.ga4gh.DAO.document;

import com.emc.ga4gh.DAO.CrudDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DTO.OEntity;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Created by Elizaveta Belokopytova.
 */

@OTransacrional
public abstract class AbstractDocumentDAO<T extends OEntity> implements CrudDAO<T> {

    @Override
    public String create(T newInstance) {
        ODocument document = createDocument(newInstance);
        document = document.save();
        return document.getIdentity().toString();
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

    protected abstract ODocument createDocument(T newInstance);
}
