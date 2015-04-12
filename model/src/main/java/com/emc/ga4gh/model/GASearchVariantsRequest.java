package com.emc.ga4gh.model;

public class GASearchVariantsRequest {

    private String[] variantSetIds = new String[0];
    private String variantName = null;
    private String[] callSetIds = new String[0];
    private String referenceName;
    private long start;
    private long end;
    private Integer pageSize = null;
    private String pageToken = null;

    public GASearchVariantsRequest(String[] variantSetIds, String variantName,
                                   String[] callSetIds, String referenceName, long start, long end,
                                   Integer pageSize, String pageToken) {
        super();
        this.variantSetIds = variantSetIds;
        this.variantName = variantName;
        this.callSetIds = callSetIds;
        this.referenceName = referenceName;
        this.start = start;
        this.end = end;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchVariantsRequest() {
    }

    public String[] getVariantSetIds() {
        return variantSetIds;
    }

    public void setVariantSetIds(String[] variantSetIds) {
        this.variantSetIds = variantSetIds;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String[] getCallSetIds() {
        return callSetIds;
    }

    public void setCallSetIds(String[] callSetIds) {
        this.callSetIds = callSetIds;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
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
