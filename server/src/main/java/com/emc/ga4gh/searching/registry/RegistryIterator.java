package com.emc.ga4gh.searching.registry;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;


public class RegistryIterator <T> implements Iterator<T> {
	private int filePos = 0;
	private String content;
	private Class<T> clazz;
	
	public RegistryIterator (File file, Class<T> clazz) {
		this.clazz = clazz;
		try {
			content = new String(Files.readAllBytes(Paths.get((file
					.getPath()))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		if (filePos < content.length() - 2) {
			return true;
		}
		return false;
	}

	@Override
	public T next() {
		T entity = new Gson().fromJson(
				content.substring(filePos, content.indexOf("}", filePos) + 1),
				clazz);
		filePos = content.indexOf("}", filePos) + 1;
		return entity;
	}

	@Override
	public void remove() {		
	}

}
