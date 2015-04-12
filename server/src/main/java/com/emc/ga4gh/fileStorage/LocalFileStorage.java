package com.emc.ga4gh.fileStorage;

import java.io.File;

/**
 * Created by Elizaveta Belokopytova.
 */
public class LocalFileStorage implements FileStorage {

    @Override
    public File getFile(String path) {
        return new File(path);
    }
}
