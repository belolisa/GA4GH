package com.emc.ga4gh.indexing.variant;

import com.emc.ga4gh.DAO.VariantDAO;
import com.emc.ga4gh.DTO.Variant;
import com.emc.ga4gh.fileStorage.FileStorage;
import com.emc.ga4gh.indexing.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 15.04.15.
 */

@Component
public class OrientVariantIndexer implements Indexer {

    @Autowired
    VariantDAO vd;

    @Autowired
    FileStorage fs;

    public void index(File file) {
        List<Variant> variantList = new VariantFileParser(file).parse();
        for (Variant reference : variantList) {
            vd.create(reference);
        }
    }

    public void index(String path) {
        index(fs.getFile(path));
    }

}
