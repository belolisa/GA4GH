package com.emc.ga4gh.DAO.builder;

/**
 * Created by liza on 20.04.15.
 */
public interface SelectBuilder extends SQLBuilder {

    SelectBuilder setSelect(String select);

    SelectBuilder setOrder(String order);
}
