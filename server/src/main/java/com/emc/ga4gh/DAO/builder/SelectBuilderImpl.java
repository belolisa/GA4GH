package com.emc.ga4gh.DAO.builder;

/**
 * Created by liza on 16.04.15.
 */
public class SelectBuilderImpl extends AbstractSQLBuilder implements SelectBuilder {

    public SelectBuilderImpl(String from) {
        setQueryParameter("from", from);
    }

    @Override
    public SelectBuilder setSelect(String select) {
        setQueryParameter("select", select);
        return this;
    }

    @Override
    public SelectBuilder setOrder(String order) {
        setQueryParameter("order by", order);
        return this;
    }

    @Override
    public String build() {
        return "select " + (getQueryParameters().containsKey("select") ? getQueryParameters().get("select") : "") +
                (getQueryParameters().containsKey("from") ? "from" + " " + getQueryParameters().get("from") : "") +
                (!getObjectParameters().isEmpty() ? " where " + buildObjectParamString(): "") +
                (getQueryParameters().containsKey("limit") ? " " + "limit" + " " + getQueryParameters().get("limit") : "") +
                (getQueryParameters().containsKey("order by") ? " " + "order by" + " " + getQueryParameters().get("order by") : "");
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
