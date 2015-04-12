package com.emc.ga4gh.model;

public class GASearchVariantsResponse {

    private GAVariant[] variants = new GAVariant[0];
    private String nextPageToken = null;

    public GASearchVariantsResponse(GAVariant[] variants, String nextPageToken) {
        super();
        this.variants = variants;
        this.nextPageToken = nextPageToken;
    }

    public GASearchVariantsResponse() {
    }

    public GAVariant[] getVariants() {
        return variants;
    }

    public void setVariants(GAVariant[] variants) {
        this.variants = variants;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
