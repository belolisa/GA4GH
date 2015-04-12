package com.emc.ga4gh.model;

public class GASearchReferencesRequest {

    protected String[] md5checksums = new String[0];
    protected String[] accessions = new String[0];
    protected Integer pageSize = null;
    private String pageToken = null;

    public GASearchReferencesRequest(String[] md5checksums,
                                     String[] accessions, Integer pageSize, String pageToken) {
        super();
        this.md5checksums = md5checksums;
        this.accessions = accessions;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchReferencesRequest() {
    }

    public String[] getMd5checksums() {
        return md5checksums;
    }

    public void setMd5checksums(String[] md5checksums) {
        this.md5checksums = md5checksums;
    }

    public String[] getAccessions() {
        return accessions;
    }

    public void setAccessions(String[] accessions) {
        this.accessions = accessions;
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
