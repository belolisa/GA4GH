package com.emc.ga4gh.searching.registry.vcf;

import com.emc.sk.ga4ghapi.fileStorage.registry.Registry;
import com.emc.sk.ga4ghapi.fileStorage.registry.RegistryIterator;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class VCFRegistry extends Registry<VCFRegistryEntity>
		implements Iterable<VCFRegistryEntity>{
	
	private final String FILE_NAME = "ga4ghVCFRegistry.txt";

	public VCFRegistry(String dirPath){
		init(FILE_NAME, dirPath);
	}
	
	public VCFRegistryEntity getObjectByIndex(String index) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get((registryFile
					.getPath()))));
			Gson json = new Gson();
			int i = 0;
			while (i <= content.lastIndexOf("{")) {
				VCFRegistryEntity entity = new Gson().fromJson(
						content.substring(i, content.indexOf("}", i) + 1),
						VCFRegistryEntity.class);
				if (entity.getVariantSetId().equals(index)) {
					return entity;
				}
				i = content.indexOf("}", i) + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public VCFRegistryEntity getObjectByPath(String path) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get((registryFile
					.getPath()))));
			Gson json = new Gson();
			int i = 0;
			while (i <= content.lastIndexOf("{")) {
				VCFRegistryEntity entity = new Gson().fromJson(
						content.substring(i, content.indexOf("}", i) + 1),
						VCFRegistryEntity.class);
				if (entity.getPath().equals(path)) {
					return entity;
				}
				i = content.indexOf("}", i) + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterator<VCFRegistryEntity> iterator() {
		return new RegistryIterator<VCFRegistryEntity>(registryFile, VCFRegistryEntity.class);
	}	

}
