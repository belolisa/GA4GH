package com.emc.ga4gh.searching.getReferenceSet;

import com.emc.sk.ga4ghapi.fileStorage.registry.reference.ReferenceSetRegistry;
import com.emc.sk.ga4ghapi.model.GAReferenceSet;

/**
 * Created by timofb on 4/7/2015.
 */
public class ReferenceSetGetter {
    private ReferenceSetRegistry registry;

    public ReferenceSetGetter(String registryPath) {
        this.registry = new ReferenceSetRegistry(registryPath);
    }

    public GAReferenceSet getReference(String id) {
        return registry.getObjectByIndex(id);
    }
}
