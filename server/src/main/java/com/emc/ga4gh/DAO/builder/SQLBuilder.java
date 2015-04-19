package com.emc.ga4gh.DAO.builder;

/**
 * Created by liza on 16.04.15.
 */
public interface SQLBuilder<T> {

    SQLBuilder setObjectParameter(String name, String value);

    SQLBuilder setQueryParameter(String name, String value);

    String build();

}
