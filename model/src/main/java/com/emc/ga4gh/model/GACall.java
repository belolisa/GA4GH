package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GACall {

    private String callSetId;
    private String callSetName = null;
    private int[] genotype = new int[0];
    private String phaseset = null;
    private double[] genotypeLikelihood = new double[0];
    private Map<String, Object> info = new HashMap<String, Object>();

    public GACall(String callSetId, String callSetName, int[] genotype,
                  String phaseset, double[] genotypeLikelihood,
                  Map<String, Object> info) {
        super();
        this.callSetId = callSetId;
        this.callSetName = callSetName;
        this.genotype = genotype;
        this.phaseset = phaseset;
        this.genotypeLikelihood = genotypeLikelihood;
        this.info = info;
    }

    public GACall() {
    }

    public String getCallSetId() {
        return callSetId;
    }

    public void setCallSetId(String callSetId) {
        this.callSetId = callSetId;
    }

    public String getCallSetName() {
        return callSetName;
    }

    public void setCallSetName(String callSetName) {
        this.callSetName = callSetName;
    }

    public int[] getGenotype() {
        return genotype;
    }

    public void setGenotype(int[] genotype) {
        this.genotype = genotype;
    }

    public String getPhaseset() {
        return phaseset;
    }

    public void setPhaseset(String phaseset) {
        this.phaseset = phaseset;
    }

    public double[] getGenotypeLikelihood() {
        return genotypeLikelihood;
    }

    public void setGenotypeLikelihood(double[] genotypeLikelihood) {
        this.genotypeLikelihood = genotypeLikelihood;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
