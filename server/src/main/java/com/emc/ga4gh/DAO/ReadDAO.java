package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Read;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.springframework.stereotype.Repository;

/**
 * Created by Elizaveta Belokopytova.
 */

@Repository
public class ReadDAO extends DocumentDAO<Read> {

    @Override
    protected ODocument createDocument(Read newRead) {
        ODocument document = new ODocument();
        document.field("path", newRead.getPath());
        return document;
    }
}
