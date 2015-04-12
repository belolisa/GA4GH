package com.emc.ga4gh.DAO.impl;

import com.emc.ga4gh.DAO.AbstractDocumentDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.spring.aop.logger.Loggable;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.springframework.stereotype.Repository;

/**
 * Created by Elizaveta Belokopytova.
 */

@Repository
public class ReadDAOImpl extends AbstractDocumentDAO<Read> implements ReadDAO {

    @Override
    protected ODocument createDocument(Read newRead) {
        ODocument document = new ODocument();
        document.field("path", newRead.getPath());
        return document;
    }

    @Loggable
    @OTransacrional
    @Override
    public String create(Read newInstance) {
        return super.create(newInstance);
    }

    @Loggable
    @OTransacrional
    @Override
    public Read read(String id) {
        return super.read(id);
    }

    @Loggable
    @OTransacrional
    @Override
    public void update(Read transientObject) {
        super.update(transientObject);
    }

    @Loggable
    @OTransacrional
    @Override
    public void delete(Read persistentObject) {
        super.delete(persistentObject);
    }
}
