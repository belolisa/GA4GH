package com.emc.ga4gh.searching.getReferenceSet;


import com.emc.ga4gh.model.GAReferenceSet;
import com.emc.ga4gh.searching.registry.reference.ReferenceSetRegistry;

public class ReferenceSetGetter {
    private ReferenceSetRegistry registry;

    public ReferenceSetGetter(String registryPath) {
        this.registry = new ReferenceSetRegistry(registryPath);
    }

    public GAReferenceSet getReference(String id) {
        return registry.getObjectByIndex(id);
    }
}
