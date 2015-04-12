package com.emc.ga4gh.model;

public class GAListReferenceBasesResponse {

    private long offset = 0;
    private String sequence = null;
    private String nextPageToken = null;

    public GAListReferenceBasesResponse(long offset, String sequence,
                                        String nextPageToken) {
        super();
        this.offset = offset;
        this.sequence = sequence;
        this.nextPageToken = nextPageToken;
    }

    public GAListReferenceBasesResponse() {
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
