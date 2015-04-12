package com.emc.ga4gh.model;

public class GASearchReferenceSetsRequest {

    private String[] md5checksums = new String[0];
    private String[] accessions = new String[0];
    private String assemblyId = null;
    private Integer pageSize = null;
    private String pageToken = null;

    public GASearchReferenceSetsRequest(String[] md5checksums,
                                        String[] accessions, String assemblyId, Integer pageSize,
                                        String pageToken) {
        super();
        this.md5checksums = md5checksums;
        this.accessions = accessions;
        this.assemblyId = assemblyId;
        this.pageSize = pageSize;
        this.pageToken = pageToken;
    }

    public GASearchReferenceSetsRequest() {
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

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
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
