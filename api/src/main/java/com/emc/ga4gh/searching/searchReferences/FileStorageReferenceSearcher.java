package com.emc.ga4gh.searching.searchReferences;

import java.io.File;

/**
 * Created by timofb on 4/6/2015.
 */
public class FileStorageReferenceSearcher {

    private String path;
    private static final String REFERENCES_FILE_EXTENTION = ".fasta";
    private static String previousRequestFilePath;

    private GASearchReferencesResponse getResponseFromFS(File file, GASearchReferencesRequest request) {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                GASearchReferencesResponse dirResult = getResponseFromFS(childFile, request);
                if (dirResult != null) {
                    return dirResult;
                }
            }
        } else {
            if ((file.getName().endsWith(REFERENCES_FILE_EXTENTION))) {
                FileReferenceSearcher searcher = new FileReferenceSearcher(file);
                GASearchReferencesResponse response = searcher.searchReferences(request);
                if (response != null) {
                    previousRequestFilePath = file.getPath();
                }
                return response;
            }
        }
        return null;
    }

    public FileStorageReferenceSearcher(String path) {
        this.path = path;
    }

    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request) {
        if ((request.getPageToken() != null) && (previousRequestFilePath != null)) {
            return new FileReferenceSearcher(new File(previousRequestFilePath)).searchReferences(request);
        }
        return getResponseFromFS(new File(path), request);
    }
}
