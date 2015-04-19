package com.emc.ga4gh.searching.getReferenceBases;

/**
 * Created by timofb on 4/6/2015.
 */
public class ReferenceBasesPageToken {

    private String id;
    private long offset;
    private Long end;

    public ReferenceBasesPageToken(String id, long offset, Long end) {
        this.id = id;
        this.offset = offset;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public long getOffset() {
        return offset;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
