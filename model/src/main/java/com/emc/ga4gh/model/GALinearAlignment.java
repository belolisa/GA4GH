package com.emc.ga4gh.model;

public class GALinearAlignment {

    private GAPosition position;
    private Integer mappingQuality = null;
    private GACigarUnit[] cigar = new GACigarUnit[0];

    public GALinearAlignment(GAPosition position, Integer mappingQuality,
                             GACigarUnit[] cigar) {
        super();
        this.position = position;
        this.mappingQuality = mappingQuality;
        this.cigar = cigar;
    }

    public GALinearAlignment() {
    }

    public GAPosition getPosition() {
        return position;
    }

    public void setPosition(GAPosition position) {
        this.position = position;
    }

    public Integer getMappingQuality() {
        return mappingQuality;
    }

    public void setMappingQuality(Integer mappingQuality) {
        this.mappingQuality = mappingQuality;
    }

    public GACigarUnit[] getCigar() {
        return cigar;
    }

    public void setCigar(GACigarUnit[] cigar) {
        this.cigar = cigar;
    }
}
