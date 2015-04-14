package com.emc.ga4gh.indexing.read;

import com.emc.ga4gh.DTO.Read;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileReadsParserTest {

    private FileReadsParser fileReadsSearcher;

    @Before
    public void setUp() throws Exception {
        fileReadsSearcher = new FileReadsParser(new File("file:example.bam"));
    }

    @Test
    public void testSearch() throws Exception {
        List<Read> search = fileReadsSearcher.parse();
        System.out.println(search);
    }
}