package com.emc.ga4gh.model;

import java.util.HashMap;
import java.util.Map;

public class GAVariantSetMetadata {

    private String key;
    private String value;
    private String id;
    private String type;
    private String number;
    private String description;
    private Map<String, Object> info = new HashMap<String, Object>();

    public GAVariantSetMetadata(String key, String value, String id,
                                String type, String number, String description,
                                Map<String, Object> info) {
        super();
        this.key = key;
        this.value = value;
        this.id = id;
        this.type = type;
        this.number = number;
        this.description = description;
        this.info = info;
    }

    public GAVariantSetMetadata() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
