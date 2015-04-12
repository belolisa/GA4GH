package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GAReadGroup {

    private String id;
    private String datasetId = null;
    private String name = null;
    private String description = null;
    private String sampleId;
    private GAExperiment experiment;
    private Integer predictedInsertSize = null;
    private Long created = null;
    private Long updated = null;
    private GAProgram[] programs = new GAProgram[0];
    private String referenceSetId = null;
    private Map<String, Object> info = new HashMap<String, Object>();

    public GAReadGroup(String id, String datasetId, String name,
                       String description, String sampleId, GAExperiment experiment,
                       Integer predictedInsertSize, Long created, Long updated,
                       GAProgram[] programs, String referenceSetId,
                       Map<String, Object> info) {
        super();
        this.id = id;
        this.datasetId = datasetId;
        this.name = name;
        this.description = description;
        this.sampleId = sampleId;
        this.experiment = experiment;
        this.predictedInsertSize = predictedInsertSize;
        this.created = created;
        this.updated = updated;
        this.programs = programs;
        this.referenceSetId = referenceSetId;
        this.info = info;
    }

    public GAReadGroup() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public GAExperiment getExperiment() {
        return experiment;
    }

    public void setExperiment(GAExperiment experiment) {
        this.experiment = experiment;
    }

    public Integer getPredictedInsertSize() {
        return predictedInsertSize;
    }

    public void setPredictedInsertSize(Integer predictedInsertSize) {
        this.predictedInsertSize = predictedInsertSize;
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

    public GAProgram[] getPrograms() {
        return programs;
    }

    public void setPrograms(GAProgram[] programs) {
        this.programs = programs;
    }

    public String getReferenceSetId() {
        return referenceSetId;
    }

    public void setReferenceSetId(String referenceSetId) {
        this.referenceSetId = referenceSetId;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
