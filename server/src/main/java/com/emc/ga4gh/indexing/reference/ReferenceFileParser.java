package com.emc.ga4gh.indexing.reference;

import com.emc.ga4gh.DTO.Reference;
import com.emc.ga4gh.indexing.Parser;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 16.04.15.
 */
public class ReferenceFileParser implements Parser<Reference> {

    private final File file;

    public ReferenceFileParser(File file) {
        this.file = file;
    }

    @Override
    public List<Reference> parse() {
        return null;
    }
}
