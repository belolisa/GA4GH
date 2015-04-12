package com.emc.ga4gh.fileStorage;

import java.io.File;

/**
 * Created by Elizaveta Belokopytova.
 */
public interface FileStorage {

    File getFile(String path);

}
