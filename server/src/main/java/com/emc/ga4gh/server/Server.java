package com.emc.ga4gh.server;

import com.emc.ga4gh.model.*;

public interface Server {

    public GAReference getReference(String id);

    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request);

    public GAReferenceSet getReferenceSet(String id);

    public GASearchCallSetsResponse searchCallSets(GASearchCallSetsRequest request);

    public GASearchReadGroupSetsResponse searchReadGroupSets(GASearchReadGroupSetsRequest request);

    public GASearchReadsResponse searchReads(GASearchReadsRequest request);

    public GASearchReferenceSetsResponse searchReferenceSets(GASearchReferenceSetsRequest request);

    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request);

    public GASearchVariantSetsResponse searchVariantSets(GASearchVariantSetsRequest request);

    public GASearchVariantsResponse searchVariants(GASearchVariantsRequest request);
}
