package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.OEntity;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Created by Elizaveta Belokopytova.
 */

public abstract class AbstractDocumentDAO<T extends OEntity> implements DocumentDAO<T> {

//    private static final Logger logger = LoggerFactory.getLogger(ObjectDAO.class);

    @Override
    public String create(T newInstance) {
//        logger.debug("DocumentDAO.create invoked");
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
