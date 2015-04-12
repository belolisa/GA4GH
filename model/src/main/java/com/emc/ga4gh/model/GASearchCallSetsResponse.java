package com.emc.ga4gh.model;

public class GASearchCallSetsResponse {

    private GACallSet[] callSets = new GACallSet[0];
    private String nextPageToken = null;

    public GASearchCallSetsResponse(GACallSet[] callSets, String nextPageToken) {
        super();
        this.callSets = callSets;
        this.nextPageToken = nextPageToken;
    }

    public GASearchCallSetsResponse() {
    }

    public GACallSet[] getCallSets() {
        return callSets;
    }

    public void setCallSets(GACallSet[] callSets) {
        this.callSets = callSets;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
