package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GAVariant {

    private String id;
    private String variantSetId;
    private String[] names = new String[0];
    private Long created = null;
    private Long updated = null;
    private String referenceName;
    private long start;
    private long end;
    private String referenceBases;
    private String[] alternateBases = new String[0];
    private Map<String, Object> info = new HashMap<String, Object>();
    private GACall[] calls = new GACall[0];

    public GAVariant(String id, String variantSetId, String[] names,
                     Long created, Long updated, String referenceName, long start,
                     long end, String referenceBases, String[] alternateBases,
                     Map<String, Object> info, GACall[] calls) {
        super();
        this.id = id;
        this.variantSetId = variantSetId;
        this.names = names;
        this.created = created;
        this.updated = updated;
        this.referenceName = referenceName;
        this.start = start;
        this.end = end;
        this.referenceBases = referenceBases;
        this.alternateBases = alternateBases;
        this.info = info;
        this.calls = calls;
    }

    public GAVariant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVariantSetId() {
        return variantSetId;
    }

    public void setVariantSetId(String variantSetId) {
        this.variantSetId = variantSetId;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
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

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getReferenceBases() {
        return referenceBases;
    }

    public void setReferenceBases(String referenceBases) {
        this.referenceBases = referenceBases;
    }

    public String[] getAlternateBases() {
        return alternateBases;
    }

    public void setAlternateBases(String[] alternateBases) {
        this.alternateBases = alternateBases;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public GACall[] getCalls() {
        return calls;
    }

    public void setCalls(GACall[] calls) {
        this.calls = calls;
    }
}
