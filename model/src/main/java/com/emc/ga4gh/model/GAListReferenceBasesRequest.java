package com.emc.ga4gh.model;

/**
 * Created by timofb on 4/6/2015.
 */
public class GAListReferenceBasesRequest {

    private long start = 0;
    private Long end = null;
    private String pageToken = null;

    public GAListReferenceBasesRequest(long start, Long end, String nextPageToken) {
        super();
        this.start = start;
        this.end = end;
        this.pageToken = nextPageToken;
    }

    public GAListReferenceBasesRequest() {
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}
