package com.emc.ga4gh.model;

public class GASearchReadsResponse {

    private GAReadAlignment[] alignments = new GAReadAlignment[0];
    private String nextPageToken = null;

    public GASearchReadsResponse(GAReadAlignment[] alignments,
                                 String nextPageToken) {
        super();
        this.alignments = alignments;
        this.nextPageToken = nextPageToken;
    }

    public GASearchReadsResponse() {
    }

    public GAReadAlignment[] getAlignments() {
        return alignments;
    }

    public void setAlignments(GAReadAlignment[] alignments) {
        this.alignments = alignments;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
