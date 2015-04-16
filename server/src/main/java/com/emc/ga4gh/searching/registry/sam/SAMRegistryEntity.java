package com.emc.ga4gh.searching.registry.sam;

public class SAMRegistryEntity{
	
	private String readGroupSetId;
	private String path;
	
	public SAMRegistryEntity(String readGroupSetId, String path) {
		super();
		this.readGroupSetId = readGroupSetId;
		this.path = path;
	}
	
	public String getReadGroupSetId() {
		return readGroupSetId;
	}
	public void setReadGroupSetId(String readGroupSetId) {
		this.readGroupSetId = readGroupSetId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
