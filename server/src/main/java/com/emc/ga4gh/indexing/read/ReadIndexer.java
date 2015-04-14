package com.emc.ga4gh.indexing.read;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 15.04.15.
 */

@Component
public class ReadIndexer {

    @Autowired
    ReadDAO rd;

    public void index(File file) {
        List<Read> readList = new FileReadsParser(file).parse();
        for (Read read : readList) {
            rd.create(read);
        }
    }
}
