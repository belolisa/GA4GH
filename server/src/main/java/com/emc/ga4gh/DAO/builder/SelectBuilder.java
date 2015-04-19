package com.emc.ga4gh.DAO.builder;

/**
 * Created by liza on 16.04.15.
 */
public class SelectBuilder extends AbstractSQLBuilder {

    private String select;

    private String from;

    public SelectBuilder(String from) {
        this.from = from;
    }

    public SelectBuilder setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String build() {
        return "select " + (select != null? select + " ": "") +
                "from " + from +
                (!getObjectParameters().isEmpty() ? " where " + buildObjectParamString(): "") +
                (!getQueryParameters().containsKey("limit") ? " limit " + getQueryParameters().get("limit"): "");
    }

    @Override
    protected String buildObjectParamString() {
        return getObjectParameters()
                .entrySet()
                .stream()
                .map((o) -> o.getKey() + o.getValue().getInfix() + o.getValue().getValue())
                .reduce((first, second) -> first + " AND " + second)
                .get();
    }
}
