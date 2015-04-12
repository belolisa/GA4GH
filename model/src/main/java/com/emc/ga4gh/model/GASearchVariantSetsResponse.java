package com.emc.ga4gh.model;

public class GASearchVariantSetsResponse {

    private GAVariantSet[] variantSets = new GAVariantSet[0];
    private String nextPageToken = null;

    public GASearchVariantSetsResponse(GAVariantSet[] variantSets,
                                       String nextPageToken) {
        super();
        this.variantSets = variantSets;
        this.nextPageToken = nextPageToken;
    }

    public GASearchVariantSetsResponse() {
    }

    public GAVariantSet[] getVariantSets() {
        return variantSets;
    }

    public void setVariantSets(GAVariantSet[] variantSets) {
        this.variantSets = variantSets;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
