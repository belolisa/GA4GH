package com.emc.ga4gh.model;

public class GAReferenceSet {

    private String id;
    private String[] referenceIds = new String[0];
    private String description = null;
    private String assemblyId = null;
    private String md5checksum;
    private String sourceURI = null;
    private String[] sourceAccessions;
    private boolean isDerived = false;
    private Integer ncbiTaxonId = null;

    public GAReferenceSet(String id, String[] referenceIds, String description,
                          String assemblyId, String md5checksum, String sourceURI,
                          String[] sourceAccessions, boolean isDerived, Integer ncbiTaxonId) {
        super();
        this.id = id;
        this.referenceIds = referenceIds;
        this.description = description;
        this.assemblyId = assemblyId;
        this.md5checksum = md5checksum;
        this.sourceURI = sourceURI;
        this.sourceAccessions = sourceAccessions;
        this.isDerived = isDerived;
        this.ncbiTaxonId = ncbiTaxonId;
    }

    public GAReferenceSet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getReferenceIds() {
        return referenceIds;
    }

    public void setReferenceIds(String[] referenceIds) {
        this.referenceIds = referenceIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getMd5checksum() {
        return md5checksum;
    }

    public void setMd5checksum(String md5checksum) {
        this.md5checksum = md5checksum;
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

    public Integer getNcbiTaxonId() {
        return ncbiTaxonId;
    }

    public void setNcbiTaxonId(Integer ncbiTaxonId) {
        this.ncbiTaxonId = ncbiTaxonId;
    }
}
