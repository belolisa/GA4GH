package com.emc.ga4gh.searching;

/**
 * Created by liza on 15.04.15.
 */
public interface Searcher<T, R> {

    public T search(R request);

}
