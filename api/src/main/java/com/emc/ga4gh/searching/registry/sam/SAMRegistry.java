package com.emc.ga4gh.searching.registry.sam;

import com.emc.ga4gh.searching.registry.Registry;
import com.emc.ga4gh.searching.registry.RegistryIterator;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class SAMRegistry extends Registry<SAMRegistryEntity>
		implements Iterable<SAMRegistryEntity>{

	private final String FILE_NAME = "ga4ghSAMRegistry.txt";

	public SAMRegistry(String dirPath) {
		init(FILE_NAME, dirPath);
	}
	
	public SAMRegistryEntity getObjectByIndex(String index) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get((registryFile
					.getPath()))));
			Gson json = new Gson();
			int i = 0;
			while (i <= content.lastIndexOf("{")) {
				SAMRegistryEntity entity = new Gson().fromJson(
						content.substring(i, content.indexOf("}", i) + 1),
						SAMRegistryEntity.class);
				if (entity.getReadGroupSetId().equals(index)) {
					return entity;
				}
				i = content.indexOf("}", i) + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SAMRegistryEntity getObjectByPath(String path) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get((registryFile
					.getPath()))));
			int i = 0;
			while (i <= content.lastIndexOf("{")) {
				SAMRegistryEntity entity = new Gson().fromJson(
						content.substring(i, content.indexOf("}", i) + 1),
						SAMRegistryEntity.class);
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
	public Iterator<SAMRegistryEntity> iterator() {
		return new RegistryIterator<SAMRegistryEntity>(registryFile, SAMRegistryEntity.class);
	}

}
