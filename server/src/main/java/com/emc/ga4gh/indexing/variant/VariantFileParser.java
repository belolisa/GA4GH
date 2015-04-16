package com.emc.ga4gh.indexing.variant;

import com.emc.ga4gh.DTO.Variant;
import com.emc.ga4gh.indexing.Parser;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 16.04.15.
 */
public class VariantFileParser implements Parser<Variant> {

    private final File file;

    public VariantFileParser(File file) {
        this.file = file;
    }

    @Override
    public List<Variant> parse() {
        return null;
    }
}
