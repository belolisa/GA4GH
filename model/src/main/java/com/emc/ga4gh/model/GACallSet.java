package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GACallSet {

    private String id;
    private String name = null;
    ;
    private String sampleId;
    private String[] variantSetIds = new String[0];
    private Long created = null;
    private Long updated = null;
    private Map<String, Object> info = new HashMap<String, Object>();

    public GACallSet(String id, String name, String sampleId,
                     String[] variantSetIds, Long created, Long updated,
                     Map<String, Object> info) {
        super();
        this.id = id;
        this.name = name;
        this.sampleId = sampleId;
        this.variantSetIds = variantSetIds;
        this.created = created;
        this.updated = updated;
        this.info = info;
    }

    public GACallSet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String[] getVariantSetIds() {
        return variantSetIds;
    }

    public void setVariantSetIds(String[] variantSetIds) {
        this.variantSetIds = variantSetIds;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
