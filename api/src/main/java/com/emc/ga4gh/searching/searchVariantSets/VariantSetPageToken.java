package com.emc.ga4gh.searching.searchVariantSets;

import com.emc.sk.ga4ghapi.model.GASearchVariantSetsRequest;

public class VariantSetPageToken extends GASearchVariantSetsRequest{
	
	private long offset;
	
	public VariantSetPageToken(String[] datasetIds, Integer pageSize,
			long offset) {
		super();
		this.datasetIds = datasetIds;
		this.pageSize = pageSize;
		this.offset = offset;
	}

	public long getOffset() {
		return offset;
	}
}
