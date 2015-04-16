package com.emc.ga4gh.searching.registry.reference;

import com.emc.sk.ga4ghapi.fileStorage.registry.Registry;
import com.emc.sk.ga4ghapi.model.GAReferenceSet;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by timofb on 4/7/2015.
 */
public class ReferenceSetRegistry  extends Registry<GAReferenceSet> {

    private final String FILE_NAME = "referenceRegistry.txt";

    public ReferenceSetRegistry(String dirPath) {
        init(FILE_NAME,dirPath);
    }

    public GAReferenceSet getObjectByIndex(String index) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get((registryFile
                    .getPath()))));
            Gson json = new Gson();
            int i = 0;
            while (i <= content.lastIndexOf("{")) {
                GAReferenceSet entity = new Gson().fromJson(
                        content.substring(i, content.indexOf("}", i) + 1),
                        GAReferenceSet.class);
                if (entity.getId().equals(index)) {
                    return entity;
                }
                i = content.indexOf("}", i) + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
