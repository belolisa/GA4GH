package com.emc.ga4gh.model;

public class GAReference {

    private String id;
    private long length;
    private String md5checksum;
    private String name;
    private String sourceURI = null;
    private String[] sourceAccessions;
    private boolean isDerived = false;
    private Float sourceDivergence = null;
    private Integer ncbiTaxonId = null;

    public GAReference(String id, long length, String md5checksum, String name,
                       String sourceURI, String[] sourceAccessions, boolean isDerived,
                       Float sourceDivergence, Integer ncbiTaxonId) {
        super();
        this.id = id;
        this.length = length;
        this.md5checksum = md5checksum;
        this.name = name;
        this.sourceURI = sourceURI;
        this.sourceAccessions = sourceAccessions;
        this.isDerived = isDerived;
        this.sourceDivergence = sourceDivergence;
        this.ncbiTaxonId = ncbiTaxonId;
    }

    public GAReference() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMd5checksum() {
        return md5checksum;
    }

    public void setMd5checksum(String md5checksum) {
        this.md5checksum = md5checksum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }

    public String[] getSourceAccessions() {
        return sourceAccessions;
    }

    public void setSourceAccessions(String[] sourceAccessions) {
        this.sourceAccessions = sourceAccessions;
    }

    public boolean isDerived() {
        return isDerived;
    }

    public void setDerived(boolean isDerived) {
        this.isDerived = isDerived;
    }

    public Float getSourceDivergence() {
        return sourceDivergence;
    }

    public void setSourceDivergence(Float sourceDivergence) {
        this.sourceDivergence = sourceDivergence;
    }

    public Integer getNcbiTaxonId() {
        return ncbiTaxonId;
    }

    public void setNcbiTaxonId(Integer ncbiTaxonId) {
        this.ncbiTaxonId = ncbiTaxonId;
    }
}
