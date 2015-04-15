package com.emc.ga4gh.indexing.reference;

import com.emc.ga4gh.DAO.ReferenceDAO;
import com.emc.ga4gh.DTO.Reference;
import com.emc.ga4gh.indexing.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 15.04.15.
 */

@Component
public class OrientReferenceIndexer implements Indexer {

    @Autowired
    ReferenceDAO rd;

    @Override
    public void index(File file) {
        List<Reference> readList = new ReferenceFileParser(file).parse();
        for (Reference reference : readList) {
            rd.create(reference);
        }
    }

}
