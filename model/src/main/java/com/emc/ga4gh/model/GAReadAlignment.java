package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GAReadAlignment {

    private String id;
    private String readGroupId;
    private String fragmentName;
    private Boolean properPlacement = false;
    private Boolean duplicateFragment = false;
    private Integer numberReads = null;
    private Integer fragmentLength = null;
    private Integer readNumber = null;
    private Boolean failedVendorQualityChecks = false;
    private GALinearAlignment alingment = null;
    private Boolean secondaryAlignment = false;
    private Boolean supplementaryAlignment = false;
    private String alignedSequence = null;
    private int[] alignedQuality = new int[0];
    private GAPosition nextMatePosition = null;
    private Map<String, Object> info = new HashMap<String, Object>();

    public GAReadAlignment(String id, String readGroupId, String fragmentName,
                           Boolean properPlacement, Boolean duplicateFragment,
                           Integer numberReads, Integer fragmentLength, Integer readNumber,
                           Boolean failedVendorQualityChecks, GALinearAlignment alingment,
                           Boolean secondaryAlignment, Boolean supplementaryAlignment,
                           String alignedSequence, int[] alignedQuality,
                           GAPosition nextMatePosition, Map<String, Object> info) {
        super();
        this.id = id;
        this.readGroupId = readGroupId;
        this.fragmentName = fragmentName;
        this.properPlacement = properPlacement;
        this.duplicateFragment = duplicateFragment;
        this.numberReads = numberReads;
        this.fragmentLength = fragmentLength;
        this.readNumber = readNumber;
        this.failedVendorQualityChecks = failedVendorQualityChecks;
        this.alingment = alingment;
        this.secondaryAlignment = secondaryAlignment;
        this.supplementaryAlignment = supplementaryAlignment;
        this.alignedSequence = alignedSequence;
        this.alignedQuality = alignedQuality;
        this.nextMatePosition = nextMatePosition;
        this.info = info;
    }

    public GAReadAlignment() {
    }

    public GAReadAlignment(GAReadAlignment another) {
        this.id = another.id;
        this.readGroupId = another.readGroupId;
        this.fragmentName = another.fragmentName;
        this.properPlacement = another.properPlacement;
        this.duplicateFragment = another.duplicateFragment;
        this.numberReads = another.numberReads;
        this.fragmentLength = another.fragmentLength;
        this.readNumber = another.readNumber;
        this.failedVendorQualityChecks = another.failedVendorQualityChecks;
        this.alingment = another.alingment;
        this.secondaryAlignment = another.secondaryAlignment;
        this.supplementaryAlignment = another.supplementaryAlignment;
        this.alignedSequence = another.alignedSequence;
        this.alignedQuality = another.alignedQuality;
        this.nextMatePosition = another.nextMatePosition;
        this.info = another.info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadGroupId() {
        return readGroupId;
    }

    public void setReadGroupId(String readGroupId) {
        this.readGroupId = readGroupId;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public Boolean getProperPlacement() {
        return properPlacement;
    }

    public void setProperPlacement(Boolean properPlacement) {
        this.properPlacement = properPlacement;
    }

    public Boolean getDuplicateFragment() {
        return duplicateFragment;
    }

    public void setDuplicateFragment(Boolean duplicateFragment) {
        this.duplicateFragment = duplicateFragment;
    }

    public Integer getNumberReads() {
        return numberReads;
    }

    public void setNumberReads(Integer numberReads) {
        this.numberReads = numberReads;
    }

    public Integer getFragmentLength() {
        return fragmentLength;
    }

    public void setFragmentLength(Integer fragmentLength) {
        this.fragmentLength = fragmentLength;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public Boolean getFailedVendorQualityChecks() {
        return failedVendorQualityChecks;
    }

    public void setFailedVendorQualityChecks(Boolean failedVendorQualityChecks) {
        this.failedVendorQualityChecks = failedVendorQualityChecks;
    }

    public GALinearAlignment getAlingment() {
        return alingment;
    }

    public void setAlingment(GALinearAlignment alingment) {
        this.alingment = alingment;
    }

    public Boolean getSecondaryAlignment() {
        return secondaryAlignment;
    }

    public void setSecondaryAlignment(Boolean secondaryAlignment) {
        this.secondaryAlignment = secondaryAlignment;
    }

    public Boolean getSupplementaryAlignment() {
        return supplementaryAlignment;
    }

    public void setSupplementaryAlignment(Boolean supplementaryAlignment) {
        this.supplementaryAlignment = supplementaryAlignment;
    }

    public String getAlignedSequence() {
        return alignedSequence;
    }

    public void setAlignedSequence(String alignedSequence) {
        this.alignedSequence = alignedSequence;
    }

    public int[] getAlignedQuality() {
        return alignedQuality;
    }

    public void setAlignedQuality(int[] alignedQuality) {
        this.alignedQuality = alignedQuality;
    }

    public GAPosition getNextMatePosition() {
        return nextMatePosition;
    }

    public void setNextMatePosition(GAPosition nextMatePosition) {
        this.nextMatePosition = nextMatePosition;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
