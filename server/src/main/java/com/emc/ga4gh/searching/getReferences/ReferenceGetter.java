package com.emc.ga4gh.searching.getReferences;


import com.emc.ga4gh.model.GAReference;
import com.emc.ga4gh.searching.registry.reference.ReferenceRegistry;

public class ReferenceGetter {
    private ReferenceRegistry registry;

    public ReferenceGetter(String registryPath) {
        this.registry = new ReferenceRegistry(registryPath);
    }

    public GAReference getReference(String id) {
        return registry.getObjectByIndex(id);
    }
}
