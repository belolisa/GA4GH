package com.emc.ga4gh.DAO;

import java.util.List;

/**
 * Created by liza on 16.04.15.
 */
public class SelectBuilder extends AbstractSQLBuilder {

    private String what;

    private String from;

    public SelectBuilder(String from) {
        this.from = from;
    }

    public SelectBuilder setWhat(String what) {
        this.what = what;
        return this;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String build() {
        String query = "select " + what + " from " + from;
        if (!getObjectParameters().isEmpty()) {
            query += " where " + buildObjectParamString();
        }
        if (!getQueryParameters().containsKey("limit")) {
            query += " limit " + getQueryParameters().get("limit");
        }
        return query;
    }

    @Override
    protected String buildObjectParamString() {
        return getObjectParameters()
                .entrySet()
                .stream()
                .map((o) ->o.getKey() + "=" + o.getValue())
                .reduce((first, second) -> first + " AND " + second)
                .get();
    }
}
