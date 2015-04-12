package com.emc.ga4gh.model;

public class GASearchReadGroupSetsRequest {

    protected String[] datasetIds = new String[0];
    protected String name = null;
    protected Integer pageSize = null;
    private String pageToken = null;

    public GASearchReadGroupSetsRequest(String[] datasetIds, String name,
                                        Integer pageSize, String pageToken) {
        super();
        this.datasetIds = datasetIds;
        this.name = name;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchReadGroupSetsRequest() {
    }

    public String[] getDatasetIds() {
        return datasetIds;
    }

    public void setDatasetIds(String[] datasetIds) {
        this.datasetIds = datasetIds;
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
