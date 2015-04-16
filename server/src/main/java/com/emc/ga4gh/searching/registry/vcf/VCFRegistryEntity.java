package com.emc.ga4gh.searching.registry.vcf;

import java.util.ArrayList;

public class VCFRegistryEntity{	
	private String variantSetId;
	private String path;
	private ArrayList<String> callSetIndexes;
	
	public VCFRegistryEntity(String index, String path,
			ArrayList<String> callSetIndexes) {
		super();
		this.variantSetId = index;
		this.path = path;
		this.callSetIndexes = callSetIndexes;
	}

	public String getVariantSetId() {
		return variantSetId;
	}

	public void setIndex(String index) {
		this.variantSetId = index;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<String> getCallSetIndexes() {
		return callSetIndexes;
	}

	public void setCallSetIndexes(ArrayList<String> callSetIndexes) {
		this.callSetIndexes = callSetIndexes;
	}
	
}
