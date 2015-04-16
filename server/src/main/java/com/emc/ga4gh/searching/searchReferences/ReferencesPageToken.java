package com.emc.ga4gh.searching.searchReferences;

import com.emc.sk.ga4ghapi.model.GASearchReferencesRequest;

/**
 * Created by timofb on 4/6/2015.
 */
public class ReferencesPageToken extends GASearchReferencesRequest {
    private long offset;

    public ReferencesPageToken(String[] accessions, Integer pageSize,
                               long offset, String[] md5checksums) {
        super();
        this.md5checksums = md5checksums;
        this.accessions = accessions;
        this.pageSize = pageSize;
        this.offset = offset;
    }

    public long getOffset() {
        return offset;
    }
}
