package com.emc.ga4gh.model;

public class GASearchVariantSetsRequest {

    protected String[] datasetIds = new String[0];
    protected Integer pageSize = null;
    private String pageToken = null;

    public GASearchVariantSetsRequest(String[] datasetIds, Integer pageSize,
                                      String pageToken) {
        super();
        this.datasetIds = datasetIds;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchVariantSetsRequest() {
    }

    public String[] getDatasetIds() {
        return datasetIds;
    }

    public void setDatasetIds(String[] datasetIds) {
        this.datasetIds = datasetIds;
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
