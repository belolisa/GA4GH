package com.emc.ga4gh.searching.searchVariantSets;

import com.emc.sk.ga4ghapi.fileStorage.FileRecursiveSearcher;
import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistry;
import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistryEntity;
import com.emc.sk.ga4ghapi.fileStorage.searchCallSets.FileCallSetsSearcher;
import com.emc.sk.ga4ghapi.model.*;
import com.google.gson.Gson;
import org.springframework.cglib.core.Predicate;

import java.io.File;
import java.util.ArrayList;

public class FileStorageVariantSetsSearcher {
	private String path;
	private VCFRegistry vcfRegistry;
	private static String UUID = null;
	private static final String VARIANTS_FILE_EXTENTION = ".vcf";
	private static final Integer DEFAULT_PAGESIZE = 5;
	private static ArrayList<String> paths;
	private long temporaryOffset;
	private static String nextPageToken;
	
	private ArrayList<String> searchCallSetIds(String variantSetId, String filePath) {
		String[] variantSetIdsArray = {variantSetId};
		FileCallSetsSearcher callSetsSearcher = new FileCallSetsSearcher(
				new File(filePath), new String[0], variantSetId);
		ArrayList<GACallSet> callSets = callSetsSearcher
				.searchCallSets(new GASearchCallSetsRequest(variantSetIdsArray,
						null, null, null));
		ArrayList<String> callSetIds = new ArrayList<String>();
		for (GACallSet callSet : callSets) {
			callSetIds.add(callSet.getId());
		}
		return callSetIds;
	}
	
	private String makeNextPageToken(GASearchVariantSetsRequest request,
			long offset) {
		VariantSetPageToken token = new VariantSetPageToken(
				request.getDatasetIds(),
				request.getPageSize(), offset);
		Gson gson = new Gson();
		return gson.toJson(token);
	}
	
	private GASearchVariantSetsRequest makeRequestFromToken(String pageToken) {
		VariantSetPageToken token = new Gson().fromJson(pageToken,
				VariantSetPageToken.class);
		GASearchVariantSetsRequest newRequest = new GASearchVariantSetsRequest(token.getDatasetIds(),
				token.getPageSize(), null);
		temporaryOffset =  token.getOffset();
		return newRequest;		
	}
	
	/*public static ArrayList<GAVariantSet> getResponseFromFS(File file,
			int currentSize, GASearchVariantSetsRequest request) {
		Integer pageSize = request.getPageSize();
		ArrayList<GAVariantSet> result = new ArrayList<GAVariantSet>();
		if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
				ArrayList<GAVariantSet> dirResult = getResponseFromFS(childFile,
						currentSize, request);
				if (dirResult != null) {
					if (currentSize + dirResult.size() < pageSize) {
						result.addAll(dirResult);
						currentSize += dirResult.size();
					}
					else {
						for (int i = 0; i < pageSize - currentSize; i++) {
							result.add(dirResult.get(i));
						}
						currentSize = pageSize;
						return result;
					}
				}
			}
		} else if (((file.getName().endsWith(VARIANTS_FILE_EXTENTION)))) {
			result.add(new FileVariantSetsSearcher(file).searchVariants(request));
		}
		return result;
	}*/
	
	public FileStorageVariantSetsSearcher(String path, String vcfRegistryPath) {
		this.path = path;
		this.vcfRegistry = new VCFRegistry(vcfRegistryPath);
	}
	
	public GASearchVariantSetsResponse searchVariantSets(
			GASearchVariantSetsRequest request) {
		if (paths == null) {
			paths = FileRecursiveSearcher.findFilesRecursively(new File(path),
					new Predicate() {
						@Override
						public boolean evaluate(Object file) {
							return (file != null)
									&& (file instanceof File)
									&& ((File) file).isFile()
									&& (((File) file).getName().endsWith(
											VARIANTS_FILE_EXTENTION ));
						}
					});
		}
		if (request.getPageToken() != null) {
			if (request.getPageToken().equals(nextPageToken)) {
				return searchVariantSets(makeRequestFromToken(request
						.getPageToken()));
			}
		}
		ArrayList<GAVariantSet> resultSets = new ArrayList<GAVariantSet>();
		int offset = 0;
		int added = 0;
		Integer pageSize = request.getPageSize();
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		for (String filePath : paths.subList((int) temporaryOffset,
				paths.size())) {
			if (added < pageSize) {
				++offset;
				File file = new File(filePath);
					VCFRegistryEntity entity = vcfRegistry
							.getObjectByPath(filePath);
					if (entity != null) {
						++added;
						resultSets.add(new FileVariantSetsSearcher(file,
								entity.getVariantSetId()).searchVariantSets(request));
					} else {
						++added;
						GAVariantSet fileResult = new FileVariantSetsSearcher(
								file, null).searchVariantSets(request);
						resultSets.add(fileResult);
						vcfRegistry.putToRegistry(new VCFRegistryEntity(
								fileResult.getId(), filePath, searchCallSetIds(fileResult.getId(), filePath)));
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
		return new GASearchVariantSetsResponse(
				resultSets.toArray(new GAVariantSet[resultSets.size()]),
				nextPageToken);
	}
	
	
	public VCFRegistry getVcfRegistry() {
		return vcfRegistry;
	}
}
