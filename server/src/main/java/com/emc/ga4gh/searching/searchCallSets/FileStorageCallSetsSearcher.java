package com.emc.ga4gh.searching.searchCallSets;

import com.emc.sk.ga4ghapi.fileStorage.FileRecursiveSearcher;
import com.emc.sk.ga4ghapi.fileStorage.IdGenerator;
import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistry;
import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistryEntity;
import com.emc.sk.ga4ghapi.model.GACallSet;
import com.emc.sk.ga4ghapi.model.GASearchCallSetsRequest;
import com.emc.sk.ga4ghapi.model.GASearchCallSetsResponse;
import com.google.gson.Gson;
import org.springframework.cglib.core.Predicate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorageCallSetsSearcher {
	private String path;
	private VCFRegistry vcfRegistry;
	private static String UUID = null;
	private int fileResultOffset;
	private int filesOffset = 0;
	private static final String VARIANTS_FILE_EXTENTION = ".vcf";
	private static final Integer DEFAULT_PAGESIZE = 5;
	private static ArrayList<String> paths;
	private static String nextPageToken;

	private String makeNextPageToken(GASearchCallSetsRequest request,
			int fileResultOffset, int filesOffset) {
		CallSetPageToken token = new CallSetPageToken(fileResultOffset,
				filesOffset, request.getPageSize(), request.getVariantSetIds());
		Gson gson = new Gson();
		return gson.toJson(token);
	}

	private GASearchCallSetsRequest makeRequestFromToken(String pageToken) {
		CallSetPageToken token = new Gson().fromJson(pageToken,
				CallSetPageToken.class);
		GASearchCallSetsRequest newRequest = new GASearchCallSetsRequest(
				token.getVariantSetIds(), token.getName(), token.getPageSize(),
				null);
		fileResultOffset = token.getFileResultOffset();
		filesOffset = token.getFilesOffset();
		return newRequest;
	}

	public FileStorageCallSetsSearcher(String path, String vcfRegistryPath) {
		this.path = path;
		this.vcfRegistry = new VCFRegistry(vcfRegistryPath);
	}

	public VCFRegistry getVCFRegistry() {
		return this.vcfRegistry;
	}

	public GASearchCallSetsResponse searchCallSets(
			GASearchCallSetsRequest request) {
		if (request.getPageToken() != null) {
			if (request.getPageToken().equals(nextPageToken)) {
				return searchCallSets(makeRequestFromToken(request
						.getPageToken()));
			}
		}
		ArrayList<GACallSet> resultSets = new ArrayList<GACallSet>();
		Integer pageSize = request.getPageSize();
		int added = 0;
		int currentFilesOffset = -1;
		int currentFileResultOffset = 0;
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		if (request.getVariantSetIds().length == 0) {
			if (paths == null) {
				paths = FileRecursiveSearcher.findFilesRecursively(new File(
						path), new Predicate() {
					@Override
					public boolean evaluate(Object file) {
						return (file != null)
								&& (file instanceof File)
								&& ((File) file).isFile()
								&& (((File) file).getName()
										.endsWith(VARIANTS_FILE_EXTENTION));
					}
				});
			}
			for (String filePath : paths.subList(filesOffset, paths.size())) {
				++currentFilesOffset;
				VCFRegistryEntity entity = vcfRegistry
						.getObjectByPath(filePath);
				FileCallSetsSearcher searcher;
				String variantSetId = null;
				if (entity != null) {
					searcher = new FileCallSetsSearcher(new File(filePath),
							entity.getCallSetIndexes().toArray(
									new String[entity.getCallSetIndexes()
											.size()]), entity.getVariantSetId());
				}
				else {
					variantSetId = IdGenerator.generateId();
					searcher = new FileCallSetsSearcher(new File(filePath),
							new String[0], variantSetId);
				}
				List<GACallSet> fileResult = searcher
						.searchCallSets(request);
				if (entity == null) {
					vcfRegistry.putToRegistry(new VCFRegistryEntity(variantSetId, filePath, new ArrayList<String>(Arrays.asList(searcher.getCallSetIds()))));
				}
				if (fileResult != null) {
					if (currentFilesOffset == 0) {
						fileResult =  new ArrayList<GACallSet> (fileResult.subList(
								fileResultOffset, fileResult.size()));
					}
					if (fileResult.size() + added < pageSize) {
						resultSets.addAll(fileResult);
						added += fileResult.size();
					} else {
						resultSets.addAll(fileResult.subList(0, pageSize
								- added));
						currentFileResultOffset = pageSize - added;
						break;
					}
				}
			}
		} else {
			for (int i = filesOffset; i < request.getVariantSetIds().length; i++) {
				String variantSetId = request.getVariantSetIds()[i];
				VCFRegistryEntity entity = vcfRegistry
						.getObjectByIndex(variantSetId);
				if (entity != null) {
					++currentFilesOffset;
					FileCallSetsSearcher searcher = new FileCallSetsSearcher(
							new File(entity.getPath()),
							entity.getCallSetIndexes().toArray(
									new String[entity.getCallSetIndexes()
											.size()]), entity.getVariantSetId());
					List<GACallSet> fileResult = searcher
							.searchCallSets(request);
					if (fileResult != null) {
						if (currentFilesOffset == 0) {
							fileResult = new ArrayList<GACallSet>(fileResult
									.subList(fileResultOffset,
											fileResult.size()));
						}
						if (fileResult.size() + added < pageSize) {
							resultSets.addAll(fileResult);
							added += fileResult.size();
						} else {
							resultSets.addAll(fileResult.subList(0, pageSize
									- added));
							currentFileResultOffset = pageSize - added;
							break;
						}
					}

				}
			}

		}
		this.fileResultOffset = currentFileResultOffset;
		this.filesOffset = currentFilesOffset;
		if (resultSets.size() == 0) {
			nextPageToken = null;
		}
		else {
			nextPageToken = makeNextPageToken(request, fileResultOffset, filesOffset);
		}
		GACallSet[] resultArray = new GACallSet[resultSets.size()];
		resultSets.toArray(resultArray);
		return new GASearchCallSetsResponse(resultArray, nextPageToken);
	}
}
