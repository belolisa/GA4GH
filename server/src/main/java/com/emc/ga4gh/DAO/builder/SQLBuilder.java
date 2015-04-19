package com.emc.ga4gh.DAO.builder;

import java.util.List;

/**
 * Created by liza on 16.04.15.
 */
public interface SQLBuilder {

    SQLBuilder setObjectParameter(String name, String infix, String value);

    SQLBuilder setQueryParameter(String name, String value);

    SQLBuilder setObjectParameterInList(String name, List<String> values);

    AbstractSQLBuilder setObjectParameterEquals(String name, String value);

    String build();

}
