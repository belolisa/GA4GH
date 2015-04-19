package com.emc.ga4gh.indexing;

import java.io.File;

/**
 * Created by liza on 15.04.15.
 */
public interface Indexer {

    void index(File file);

    void index(String path);

}
