package com.emc.ga4gh.searching.searchReadGroupSets;

import com.emc.sk.ga4ghapi.fileStorage.FileRecursiveSearcher;
import com.emc.sk.ga4ghapi.fileStorage.registry.sam.SAMRegistry;
import com.emc.sk.ga4ghapi.fileStorage.registry.sam.SAMRegistryEntity;
import com.emc.sk.ga4ghapi.model.GAReadGroupSet;
import com.emc.sk.ga4ghapi.model.GASearchReadGroupSetsRequest;
import com.emc.sk.ga4ghapi.model.GASearchReadGroupSetsResponse;
import com.google.gson.Gson;
import org.springframework.cglib.core.Predicate;

import java.io.File;
import java.util.ArrayList;

public class FileStorageReadGroupSetsSearcher {

	private String path;
	private static final String READS_FILE_EXTENTION_1 = ".bam";
	private static final String READS_FILE_EXTENTION_2 = ".sam";
	private static final Integer DEFAULT_PAGESIZE = 5;
	private SAMRegistry samRegistry;
	private long temporaryOffset = 0;
	private static ArrayList<String> paths;
	private static String nextPageToken;
	
	private String makeNextPageToken(GASearchReadGroupSetsRequest request,
			long offset) {
		ReadGroupSetPageToken token = new ReadGroupSetPageToken(
				request.getDatasetIds(), request.getName(),
				request.getPageSize(), offset);
		Gson gson = new Gson();
		return gson.toJson(token);
	}
	
	private GASearchReadGroupSetsRequest makeRequestFromToken(String pageToken) {
		ReadGroupSetPageToken token = new Gson().fromJson(pageToken,
				ReadGroupSetPageToken.class);
		GASearchReadGroupSetsRequest newRequest = new GASearchReadGroupSetsRequest(token.getDatasetIds(),
				token.getName(),
				token.getPageSize(), null);
		temporaryOffset = token.getOffset();
		return newRequest;		
	}
	
	public FileStorageReadGroupSetsSearcher(String path, String samRegistryPath) {
		this.path = path;
		this.samRegistry = new SAMRegistry(samRegistryPath);
	}
	
	public GASearchReadGroupSetsResponse searchReadGroupSets(
			GASearchReadGroupSetsRequest request) {
		if (paths == null) {
			paths = FileRecursiveSearcher.findFilesRecursively(new File(path),
					new Predicate() {
						@Override
						public boolean evaluate(Object file) {
							return (file != null)
									&& (file instanceof File)
									&& ((File) file).isFile()
									&& (((File) file).getName().endsWith(
											READS_FILE_EXTENTION_1) || ((File) file)
											.getName().endsWith(
													READS_FILE_EXTENTION_2));
						}
					});
		}
		if (request.getPageToken() != null) {
			if (request.getPageToken().equals(nextPageToken)) {
				return searchReadGroupSets(makeRequestFromToken(request
						.getPageToken()));
			}
		}
		ArrayList<GAReadGroupSet> resultSets = new ArrayList<GAReadGroupSet>();
		int offset = 0;
		int added = 0;
		Integer pageSize = request.getPageSize();
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		String name = request.getName();
		for (String filePath : paths.subList((int) temporaryOffset,
				paths.size())) {
			if (added < pageSize) {
				++offset;
				File file = new File(filePath);
				if  ((name == null) || (file.getName().contains(name))) {
					SAMRegistryEntity entity = samRegistry
							.getObjectByPath(filePath);
					if (entity != null) {
						++added;
						resultSets.add(new FileReadGroupSetsSearcher(file,
								entity.getReadGroupSetId())
								.searchReadGroupSets(request));
					} else {
						++added;
						GAReadGroupSet fileResult = new FileReadGroupSetsSearcher(
								file, "").searchReadGroupSets(request);
						resultSets.add(fileResult);
						samRegistry.putToRegistry(new SAMRegistryEntity(
								fileResult.getId(), filePath));
					}
				}
			} else {
				break;
			}
		}
		
		if (resultSets.size() != 0) {
			nextPageToken = makeNextPageToken(request, offset);
		}
		else {
			nextPageToken = null;
		}
		temporaryOffset = 0;
		return new GASearchReadGroupSetsResponse(
				resultSets.toArray(new GAReadGroupSet[resultSets.size()]),
				nextPageToken);
	}

	public SAMRegistry getRegistry() {
		return samRegistry;
	}
		
}
