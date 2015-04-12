package com.emc.ga4gh.model;

public class GAReadGroupSet {

    private String id;
    private String datasetId = null;
    private String name = null;
    private GAReadGroup[] readGroups = new GAReadGroup[0];

    public GAReadGroupSet(String id, String datasetId, String name,
                          GAReadGroup[] readGroups) {
        super();
        this.id = id;
        this.datasetId = datasetId;
        this.name = name;
        this.readGroups = readGroups;
    }

    public GAReadGroupSet() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GAReadGroup[] getReadGroups() {
        return readGroups;
    }

    public void setReadGroups(GAReadGroup[] readGroups) {
        this.readGroups = readGroups;
    }
}
