package com.emc.ga4gh.model;

public class GAExperiment {

    private String libraryId = null;
    private String platformUnit = null;
    private String sequencingCenter;
    private String instrumentModel;

    public GAExperiment(String libraryId, String platformUnit,
                        String sequencingCenter, String instrumentModel) {
        super();
        this.libraryId = libraryId;
        this.platformUnit = platformUnit;
        this.sequencingCenter = sequencingCenter;
        this.instrumentModel = instrumentModel;
    }

    public GAExperiment() {
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getPlatformUnit() {
        return platformUnit;
    }

    public void setPlatformUnit(String platformUnit) {
        this.platformUnit = platformUnit;
    }

    public String getSequencingCenter() {
        return sequencingCenter;
    }

    public void setSequencingCenter(String sequencingCenter) {
        this.sequencingCenter = sequencingCenter;
    }

    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel;
    }
}
