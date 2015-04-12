package com.emc.ga4gh.model;

public class GASearchReferenceSetsResponse {

    private GAReferenceSet[] referenceSets = new GAReferenceSet[0];
    private String nextPageToken = null;

    public GASearchReferenceSetsResponse(GAReferenceSet[] referenceSets,
                                         String nextPageToken) {
        super();
        this.referenceSets = referenceSets;
        this.nextPageToken = nextPageToken;
    }

    public GASearchReferenceSetsResponse() {
    }

    public GAReferenceSet[] getReferenceSets() {
        return referenceSets;
    }

    public void setReferenceSets(GAReferenceSet[] referenceSets) {
        this.referenceSets = referenceSets;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
