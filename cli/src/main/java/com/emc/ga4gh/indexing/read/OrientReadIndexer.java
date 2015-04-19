package com.emc.ga4gh.indexing.read;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
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
public class OrientReadIndexer implements Indexer {

    @Autowired
    ReadDAO rd;

    @Autowired
    FileStorage fs;

    public void index(File file) {
        List<Read> readList = new ReadsFileParser(file).parse();
        for (Read read : readList) {
            rd.create(read);
        }
    }

    public void index(String path) {
        index(fs.getFile(path));
    }
}
