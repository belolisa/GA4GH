package com.emc.ga4gh.searching.getReferences;

import com.emc.sk.ga4ghapi.fileStorage.registry.reference.ReferenceRegistry;
import com.emc.sk.ga4ghapi.model.GAReference;

/**
 * Created by timofb on 4/7/2015.
 */
public class ReferenceGetter {
    private ReferenceRegistry registry;

    public ReferenceGetter(String registryPath) {
        this.registry = new ReferenceRegistry(registryPath);
    }

    public GAReference getReference(String id) {
        return registry.getObjectByIndex(id);
    }
}
