package com.emc.ga4gh.model;

public class GASearchCallSetsRequest {

    protected String[] variantSetIds = new String[0];
    protected String name = null;
    protected Integer pageSize = null;
    private String pageToken = null;

    public GASearchCallSetsRequest(String[] variantSetIds, String name,
                                   Integer pageSize, String pageToken) {
        super();
        this.variantSetIds = variantSetIds;
        this.name = name;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchCallSetsRequest() {
    }

    public String[] getVariantSetIds() {
        return variantSetIds;
    }

    public void setVariantSetIds(String[] variantSetIds) {
        this.variantSetIds = variantSetIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}
