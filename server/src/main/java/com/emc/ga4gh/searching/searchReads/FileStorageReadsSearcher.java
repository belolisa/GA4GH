package com.emc.ga4gh.searching.searchReads;

import com.emc.sk.ga4ghapi.model.GASearchReadsRequest;
import com.emc.sk.ga4ghapi.model.GASearchReadsResponse;

import java.io.File;

public class FileStorageReadsSearcher {
	
	private String path;
	private static final String READS_FILE_EXTENTION_1 = ".bam";
	private static final String READS_FILE_EXTENTION_2 = ".sam";
	private static String previousRequestFilePath;
	
	public FileStorageReadsSearcher(String path) {
		this.path = path;
	}
	
	private GASearchReadsResponse getResponseFromFS(File file, GASearchReadsRequest request) {
		if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
				GASearchReadsResponse dirResult = getResponseFromFS(childFile, request);
				if (dirResult.getAlignments().length != 0) {
					return dirResult;
				}
			}
		} else {
			if ((file.getName().endsWith(READS_FILE_EXTENTION_1)) || (file.getName().endsWith(READS_FILE_EXTENTION_2))) {
				FileReadsSearcher searcher = new FileReadsSearcher(file);
				GASearchReadsResponse response = searcher.searchReads(request);
				if (response.getAlignments().length != 0) {
					previousRequestFilePath = file.getAbsolutePath();
				}
				return response;
			}
		}
		return new GASearchReadsResponse();	
	}
	
	public GASearchReadsResponse searchReads(GASearchReadsRequest request) {
		if ((request.getPageToken() != null) && (previousRequestFilePath != null)) {
			return new FileReadsSearcher(new File(previousRequestFilePath)).searchReads(request);
		}
		return getResponseFromFS(new File(path), request);
	}

}
