package com.emc.ga4gh.model;

public class GAException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;
    private int errorCode = -1;

    public GAException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
