package com.emc.ga4gh.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liza on 16.04.15.
 */
public abstract class AbstractSQLBuilder implements SQLBuilder {

    private Map<String, String> objectParameters;

    private Map<String, String> queryParameters;

    public AbstractSQLBuilder() {
        objectParameters = new HashMap<>();
        queryParameters = new HashMap<>();
    }

    public AbstractSQLBuilder setObjectParameter(String name, String value) {
        getObjectParameters().put(name, value);
        return this;
    }

    public AbstractSQLBuilder setQueryParameter(String name, String value) {
        getQueryParameters().put(name, value);
        return this;
    }

    public abstract String build();

    protected abstract String buildObjectParamString();

    protected Map<String, String> getObjectParameters() {
        return objectParameters;
    }

    protected Map<String, String> getQueryParameters() {
        return queryParameters;
    }
}
