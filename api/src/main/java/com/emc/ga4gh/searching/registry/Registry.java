package com.emc.ga4gh.searching.registry;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Registry<T> {
	protected File registryFile;
	
	public void deleteRegistry() {
		this.registryFile.delete();
	}
	
	public String getFilePath() {
		return registryFile.getAbsolutePath();
	}
	
	public void cleanRegistry() {
		try {
			PrintWriter registryCleaner = new PrintWriter(registryFile);
			registryCleaner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init(String fileName, String dirPath) {
		String registryFilePath = dirPath;
		if (System.getProperty("os.name").startsWith("Windows")) {
			registryFilePath += fileName;
		} else {
			registryFilePath += File.separator + fileName;
		}
		try {
			registryFile = new File(registryFilePath);
			if (!registryFile.exists()) {
				registryFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		return ((!registryFile.exists()) || (registryFile.length() == 0));
	}

	public void putToRegistry(T entity) {
		Gson json = new Gson();
		try {
			FileWriter writer = new FileWriter(registryFile.getAbsoluteFile(), true);
			writer.write(json.toJson(entity));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
