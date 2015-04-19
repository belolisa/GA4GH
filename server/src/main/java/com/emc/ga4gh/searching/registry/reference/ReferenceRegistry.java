package com.emc.ga4gh.searching.registry.reference;

import com.emc.ga4gh.model.GAReference;
import com.emc.ga4gh.searching.registry.Registry;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ReferenceRegistry extends Registry<GAReference> {

    private final String FILE_NAME = "referenceRegistry.txt";

    public ReferenceRegistry(String dirPath) {
        init(FILE_NAME,dirPath);
    }

    public ReferenceRegistry(String dirPath, String fileName) {
        init(fileName,dirPath);
    }

    public GAReference getObjectByIndex(String index) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get((registryFile
                    .getPath()))));
            Gson json = new Gson();
            int i = 0;
            while (i <= content.lastIndexOf("{")) {
                GAReference entity = new Gson().fromJson(
                        content.substring(i, content.indexOf("}", i) + 1),
                        GAReference.class);
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

    public GAReference getObjectByCheckSumsAndAcessions(String[] checkSums, String[] sourceAcessions) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get((registryFile
                    .getPath()))));
            Gson json = new Gson();
            int i = 0;
            while (i <= content.lastIndexOf("{")) {
                GAReference entity = new Gson().fromJson(
                        content.substring(i, content.indexOf("}", i) + 1),
                        GAReference.class);
                if ((Arrays.asList(checkSums).contains(entity.getMd5checksum())) &&
                        (Arrays.asList(sourceAcessions).containsAll(Arrays.asList(entity.getSourceAccessions())))) {
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
