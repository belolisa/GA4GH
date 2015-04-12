package com.emc.ga4gh.model;

public class GAPosition {

    private String referenceName;
    private long position;
    private boolean reverseStrand;

    public GAPosition(String referenceName, long position, boolean reverseStrand) {
        super();
        this.referenceName = referenceName;
        this.position = position;
        this.reverseStrand = reverseStrand;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public boolean isReverseStrand() {
        return reverseStrand;
    }

    public void setReverseStrand(boolean reverseStrand) {
        this.reverseStrand = reverseStrand;
    }
}
