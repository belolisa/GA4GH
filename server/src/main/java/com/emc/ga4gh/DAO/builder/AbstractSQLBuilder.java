package com.emc.ga4gh.DAO.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liza on 16.04.15.
 */
public abstract class AbstractSQLBuilder implements SQLBuilder {

    private Map<String, ParamStatement> objectParameters;

    private Map<String, String> queryParameters;

    public AbstractSQLBuilder() {
        objectParameters = new HashMap<>();
        queryParameters = new HashMap<>();
    }

    @Override
    public AbstractSQLBuilder setObjectParameter(String name, String infix, String value) {
        getObjectParameters().put(name, new ParamStatement(name, infix, value));
        return this;
    }

    @Override
    public AbstractSQLBuilder setQueryParameter(String name, String value) {
        getQueryParameters().put(name, value);
        return this;
    }

    @Override
    public AbstractSQLBuilder setObjectParameterInList(String name, List<String> values) {
        if (!values.isEmpty()) {
            getObjectParameters().put(name, new ParamStatement(name,
                    "in",
                    "(" + values
                            .stream()
                            .reduce((a, b) -> a + ", " + b)
                            .get() + ")"));
        }
        return this;
    }

    @Override
    public AbstractSQLBuilder setObjectParameterEquals(String name, String value) {
        getObjectParameters().put(name, new ParamStatement(name, "=", value));
        return this;
    }

    public abstract String build();

    protected abstract String buildObjectParamString();

    protected Map<String, ParamStatement> getObjectParameters() {
        return objectParameters;
    }

    protected Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    protected class ParamStatement {

        String param;

        String infix;

        String value;

        public ParamStatement(String param, String infix, String value) {
            this.param = param;
            this.infix = infix;
            this.value = value;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getInfix() {
            return infix;
        }

        public void setInfix(String infix) {
            this.infix = infix;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
