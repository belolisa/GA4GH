package com.emc.ga4gh.DAO.orient.document.read;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DAO.orient.document.AbstractDocumentDAO;
import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.spring.aop.logger.Log;
import com.orientechnologies.orient.core.record.impl.ODocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Elizaveta Belokopytova.
 */

public class ReadDAOImpl extends AbstractDocumentDAO<Read> implements ReadDAO {

    @Override
    protected ODocument createDocument(Read newRead) {
        ODocument document = new ODocument();
        document.field("path", newRead.getPath());
        return document;
    }

    @Log
    @Override
    public Read create(Read newInstance) {
        return super.create(newInstance);
    }

    @Log
    @Override
    public Optional<Read> read(String id) {
        return super.read(id);
    }

    @Log
    @Override
    public void update(Read transientObject) {
        super.update(transientObject);
    }

    @Log
    @Override
    public void delete(Read persistentObject) {
        super.delete(persistentObject);
    }


    @Override
    public List<Read> findIncOrdered(String referenceId, String referenceName, Long start, Long end, List<String> readGroupIds) {
            return new ArrayList<>();
        }
}
