package com.emc.ga4gh.indexing;

import com.emc.ga4gh.DTO.Entity;

import java.io.File;
import java.util.List;

/**
 * Created by liza on 15.04.15.
 */
public interface Parser<T extends Entity> {

    public abstract List<T> parse();

}
