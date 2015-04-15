package com.emc.ga4gh.server;

import com.emc.ga4gh.model.*;
import org.springframework.stereotype.Component;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
public class ServerImpl implements Server {
    @Override
    public GAReference getReference(String id) {
        return null;
    }

    @Override
    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request) {
        return null;
    }

    @Override
    public GAReferenceSet getReferenceSet(String id) {
        return null;
    }

    @Override
    public GASearchCallSetsResponse searchCallSets(GASearchCallSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReadGroupSetsResponse searchReadGroupSets(GASearchReadGroupSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReadsResponse searchReads(GASearchReadsRequest request) {
        return null;
    }

    @Override
    public GASearchReferenceSetsResponse searchReferenceSets(GASearchReferenceSetsRequest request) {
        return null;
    }

    @Override
    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request) {
        return null;
    }

    @Override
    public GASearchVariantSetsResponse searchVariantSets(GASearchVariantSetsRequest request) {
        return null;
    }

    @Override
    public GASearchVariantsResponse searchVariants(GASearchVariantsRequest request) {
        return null;
    }
}
