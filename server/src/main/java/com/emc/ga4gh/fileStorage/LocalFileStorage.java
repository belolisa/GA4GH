package com.emc.ga4gh.fileStorage;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
public class LocalFileStorage implements FileStorage {

    @Override
    public File getFile(String path) {
        return new File(path);
    }
}
