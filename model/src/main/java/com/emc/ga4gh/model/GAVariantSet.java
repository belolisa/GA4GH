package com.emc.ga4gh.model;

public class GAVariantSet {

    private String id;
    private String datasetId;
    private GAVariantSetMetadata[] metadata = new GAVariantSetMetadata[0];

    public GAVariantSet(String id, String datasetId,
                        GAVariantSetMetadata[] metadata) {
        super();
        this.id = id;
        this.datasetId = datasetId;
        this.metadata = metadata;
    }

    public GAVariantSet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public GAVariantSetMetadata[] getMetadata() {
        return metadata;
    }

    public void setMetadata(GAVariantSetMetadata[] metadata) {
        this.metadata = metadata;
    }
}
