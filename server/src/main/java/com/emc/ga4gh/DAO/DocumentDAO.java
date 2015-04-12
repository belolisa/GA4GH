package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.OEntity;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Created by Elizaveta Belokopytova.
 */
public abstract class DocumentDAO<T extends OEntity> implements CrudDAO<T, String> {

    @Override
    @OTransacrional
    public String create(T newInstance) {
        ODocument document = createDocument(newInstance);
        document = document.save();
        return document.getIdentity().toString();
    }

    @Override
    @OTransacrional
    public T read(String id) {

        return null;
    }

    @Override
    @OTransacrional
    public void update(T transientObject) {

    }

    @Override
    @OTransacrional
    public void delete(T persistentObject) {

    }

    protected abstract ODocument createDocument(T newInstance);
}
