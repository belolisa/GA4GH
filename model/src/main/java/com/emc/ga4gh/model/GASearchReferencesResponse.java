package com.emc.ga4gh.model;

public class GASearchReferencesResponse {

    private GAReference[] references = new GAReference[0];
    private String nextPageToken = null;

    public GASearchReferencesResponse(GAReference[] references,
                                      String nextPageToken) {
        super();
        this.references = references;
        this.nextPageToken = nextPageToken;
    }

    public GASearchReferencesResponse() {
    }

    public GAReference[] getReferences() {
        return references;
    }

    public void setReferences(GAReference[] references) {
        this.references = references;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
