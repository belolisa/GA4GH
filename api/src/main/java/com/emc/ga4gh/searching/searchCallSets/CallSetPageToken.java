package com.emc.ga4gh.searching.searchCallSets;


import com.emc.ga4gh.model.GASearchCallSetsRequest;

public class CallSetPageToken extends GASearchCallSetsRequest {
	private int fileResultOffset;
	private int filesOffset;

	public CallSetPageToken(int fileResultOffset, int filesOffset,
			Integer pageSize, String[] variantSetIds) {
		super();
		this.fileResultOffset = fileResultOffset;
		this.filesOffset = filesOffset;
		this.pageSize = pageSize;
		this.variantSetIds = variantSetIds;
	}

	public int getFileResultOffset() {
		return fileResultOffset;
	}

	public int getFilesOffset() {
		return filesOffset;
	}

}
