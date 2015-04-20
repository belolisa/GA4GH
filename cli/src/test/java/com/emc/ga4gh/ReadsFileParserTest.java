package com.emc.ga4gh;

import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.indexing.read.ReadsFileParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ReadsFileParserTest {

    private ReadsFileParser fileReadsSearcher;

    @Before
    public void setUp() throws Exception {
        fileReadsSearcher = new ReadsFileParser(new File("file:example.bam"));
    }

    @Test
    public void testSearch() throws Exception {
        List<Read> search = fileReadsSearcher.parse();
    }
}