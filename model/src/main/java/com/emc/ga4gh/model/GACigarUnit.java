package com.emc.ga4gh.model;

public class GACigarUnit {

    private GACigarOperation operation;
    private long operationLength;
    private String referenceSequence = null;

    public GACigarUnit(GACigarOperation operation, long operationLength,
                       String referenceSequence) {
        super();
        this.operation = operation;
        this.operationLength = operationLength;
        this.referenceSequence = referenceSequence;
    }

    public GACigarUnit() {
    }

    public GACigarOperation getOperation() {
        return operation;
    }

    public void setOperation(GACigarOperation operation) {
        this.operation = operation;
    }

    public long getOperationLength() {
        return operationLength;
    }

    public void setOperationLength(long operationLength) {
        this.operationLength = operationLength;
    }

    public String getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(String referenceSequence) {
        this.referenceSequence = referenceSequence;
    }
}
