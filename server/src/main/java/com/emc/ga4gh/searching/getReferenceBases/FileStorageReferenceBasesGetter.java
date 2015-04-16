package com.emc.ga4gh.searching.getReferenceBases;

import com.emc.sk.ga4ghapi.fileStorage.registry.reference.ReferenceRegistry;
import com.emc.sk.ga4ghapi.model.GAListReferenceBasesRequest;
import com.emc.sk.ga4ghapi.model.GAListReferenceBasesResponse;
import com.emc.sk.ga4ghapi.model.GAReference;

import java.io.File;

/**
 * Created by timofb on 4/6/2015.
 */
public class FileStorageReferenceBasesGetter {
    private String path;
    private static final String REFERENCES_FILE_EXTENTION = ".fasta";
    private static String previousRequestFilePath;
    private ReferenceRegistry registry;

    public FileStorageReferenceBasesGetter(String path, String registryPath) {
        this.registry = new ReferenceRegistry(registryPath);
        this.path = path;
    }

    private GAListReferenceBasesResponse getResponseFromFS(File file, String id, GAListReferenceBasesRequest request) {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                GAListReferenceBasesResponse dirResult = getResponseFromFS(childFile, id, request);
                if (dirResult != null) {
                    return dirResult;
                }
            }
        } else {
            if ((file.getName().endsWith(REFERENCES_FILE_EXTENTION))) {
                FileReferenceBasesGetter searcher = new FileReferenceBasesGetter(file);
                GAListReferenceBasesResponse response = searcher.getReferenceBases(id, request);
                if (response != null) {
                    previousRequestFilePath = file.getPath();
                }
                return response;
            }
        }
        return null;
    }

    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request) {
        GAReference reference = registry.getObjectByIndex(id);
        if (reference == null) {
            return null;
        }
        else {
            if ((request.getPageToken() != null) && (previousRequestFilePath != null)) {
                return new FileReferenceBasesGetter(new File(previousRequestFilePath)).getReferenceBases(id, request);
            }
            return getResponseFromFS(new File(path), id, request);
        }
    }
}
