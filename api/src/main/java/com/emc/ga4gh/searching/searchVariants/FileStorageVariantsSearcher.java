package com.emc.ga4gh.searching.searchVariants;

import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistry;
import com.emc.sk.ga4ghapi.fileStorage.registry.vcf.VCFRegistryEntity;
import com.emc.sk.ga4ghapi.model.GASearchVariantsRequest;
import com.emc.sk.ga4ghapi.model.GASearchVariantsResponse;

import java.io.File;
import java.util.ArrayList;

public class FileStorageVariantsSearcher {

	private String path;
	private VCFRegistry vcfRegistry;
	private static final String VARIANTS_FILE_EXTENTION = ".vcf";
	private static String previousRequestFilePath;
	
	public GASearchVariantsResponse getResponseFromFS(File file, GASearchVariantsRequest request) {
		if (file.isDirectory()) {
			for (File childFile : file.listFiles()) {
				GASearchVariantsResponse dirResult = getResponseFromFS(childFile, request);
				if (dirResult.getVariants().length!= 0) {
					return dirResult;
				}
			}
		} else {
			if ((file.getName().endsWith(VARIANTS_FILE_EXTENTION))) {
				FileVariantsSearcher searcher = new FileVariantsSearcher(file ,new ArrayList<String>(), "");
				GASearchVariantsResponse response = searcher.searchVariants(request);
				if (response.getVariants().length != 0) {
					previousRequestFilePath = file.getAbsolutePath();
				}
				return response;
			}
		}
		return new GASearchVariantsResponse();	
	}
		
	public GASearchVariantsResponse searchVariants(GASearchVariantsRequest request) {
		if ((request.getPageToken() != null) && (previousRequestFilePath != null)) {
			VCFRegistryEntity entity = vcfRegistry.getObjectByPath(previousRequestFilePath);
			if (entity != null) {
				return  new FileVariantsSearcher(
						new File(previousRequestFilePath),
						entity.getCallSetIndexes(), entity.getVariantSetId()).searchVariants(request);
			}
			else {
				return  new FileVariantsSearcher(
						new File(previousRequestFilePath),
						new ArrayList<String>(), "").searchVariants(request);				
			}
		}
		for (int i = 0; i < request.getVariantSetIds().length; i++) {
			String index = request.getVariantSetIds()[i];
			VCFRegistryEntity entity = vcfRegistry.getObjectByIndex(index);
			if (entity != null) {
				GASearchVariantsResponse fileResp = new FileVariantsSearcher(
						new File(entity.getPath()),
								entity.getCallSetIndexes(), entity.getVariantSetId()).searchVariants(request);
				if (fileResp.getVariants().length != 0) {
					previousRequestFilePath = entity.getPath();
					return fileResp;
				}
			} else {
				return new GASearchVariantsResponse();
			}
		}
		return getResponseFromFS(new File(path), request);
	}
	
	public FileStorageVariantsSearcher(String path, String vcfRegistryPath) {
		this.path = path;
		this.vcfRegistry = new VCFRegistry(vcfRegistryPath);
	}
	
	public VCFRegistry getVcfRegistry() {
		return vcfRegistry;
	}
}
