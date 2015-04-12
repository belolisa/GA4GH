package com.emc.ga4gh.model;

public class GADataset {

    private String id;
    private String description = null;

    public GADataset(String id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    public GADataset() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
