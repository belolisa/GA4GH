package com.emc.ga4gh.searching.searchReadGroupSets;


import com.emc.ga4gh.model.GASearchReadGroupSetsRequest;

public class ReadGroupSetPageToken extends GASearchReadGroupSetsRequest {
	private long offset;
	
	public ReadGroupSetPageToken(String[] dataSetIds, String name,
			Integer pageSize, long offset) {
		super();
		this.datasetIds = dataSetIds;
		this.name = name;
		this.pageSize = pageSize;
		this.offset = offset;
	}
	
	public long getOffset() {
		return offset;
	}
}
