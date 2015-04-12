package com.emc.ga4gh.model;

public class GASearchReadsRequest {

    private String[] readGroupIds = new String[0];
    private String referenceName = null;
    private String referenceId = null;
    private Long start = (long) 0;
    private Long end = null;
    private Integer pageSize = null;
    private String pageToken = null;

    public GASearchReadsRequest(String[] readGroupIds, String referenceName,
                                String referenceId, Long start, Long end, Integer pageSize,
                                String pageToken) {
        super();
        this.readGroupIds = readGroupIds;
        this.referenceName = referenceName;
        this.referenceId = referenceId;
        this.start = start;
        this.end = end;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchReadsRequest() {
    }

    public String[] getReadGroupIds() {
        return readGroupIds;
    }

    public void setReadGroupIds(String[] readGroupIds) {
        this.readGroupIds = readGroupIds;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
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
