package com.emc.ga4gh.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liza on 16.04.15.
 */
public interface SQLBuilder<T> {

    public SQLBuilder setObjectParameter(String name, String value);

    public SQLBuilder setQueryParameter(String name, String value);

    public String build();

}
