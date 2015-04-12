package com.emc.ga4gh.model;

public class GASearchReadGroupSetsResponse {

    private GAReadGroupSet[] readGroupSets = new GAReadGroupSet[0];
    private String nextPageToken = null;

    public GASearchReadGroupSetsResponse(GAReadGroupSet[] readGroupSets,
                                         String nextPageToken) {
        super();
        this.readGroupSets = readGroupSets;
        this.nextPageToken = nextPageToken;
    }

    public GAReadGroupSet[] getReadGroupSets() {
        return readGroupSets;
    }

    public void setReadGroupSets(GAReadGroupSet[] readGroupSets) {
        this.readGroupSets = readGroupSets;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
