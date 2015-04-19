package com.emc.ga4gh.searching.getReferenceBases;

import com.emc.ga4gh.model.GAListReferenceBasesRequest;
import com.emc.ga4gh.model.GAListReferenceBasesResponse;
import com.google.gson.Gson;
import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.FASTAFileReader;
import net.sf.jfasta.impl.FASTAElementIterator;

import java.io.File;
import java.io.IOException;

/**
 * Created by timofb on 4/6/2015.
 */
public class FileReferenceBasesGetter {

    private File inputFile;

    private static String nextPageToken;

    private String makeNextPageToken(String id, long offset, Long end) {
        ReferenceBasesPageToken token = new ReferenceBasesPageToken(id, offset, end);
        Gson gson = new Gson();
        return gson.toJson(token);
    }

    private ReferenceBasesPageToken getPageToken(String pageToken, Long end) {
        ReferenceBasesPageToken token = new Gson().fromJson(pageToken,
                ReferenceBasesPageToken.class);
        token.setEnd(end);
        return token;
    }

    public FileReferenceBasesGetter(File inputFile) {
        this.inputFile = inputFile;
    }

    public GAListReferenceBasesResponse getReferenceBases(String id, GAListReferenceBasesRequest request) {
        if (request.getPageToken() != null) {
            if (request.getPageToken().equals(nextPageToken)) {
                ReferenceBasesPageToken token = getPageToken(request.getPageToken(), request.getEnd());
                return getReferenceBases(token.getId(), new GAListReferenceBasesRequest(token.getOffset(), token.getEnd(), null));
            }
        }
        //TODO: fix this. Add dependence
        FASTAFileReader reader;
        try {
            reader = new FASTAFileReaderImpl(inputFile);
            final FASTAElementIterator it = reader.getIterator();
            long currentOffset = 0;
            while (it.hasNext()) {
                final FASTAElement el = it.next();
                String header = el.getHeader();
                String fileReferenceId = header.substring(0, header.indexOf(" "));
                if (id.equals(fileReferenceId)) {
                    it.close();
                    reader.close();
                    if (request.getEnd() == null) {
                        request.setEnd((long) el.getSequenceLength());
                    }
                    currentOffset = request.getEnd();
                    if (currentOffset == el.getSequenceLength()) {
                        nextPageToken = null;
                    }
                    else {
                        nextPageToken = makeNextPageToken(id, currentOffset, request.getEnd());
                    }
                    return new GAListReferenceBasesResponse(currentOffset,
                            el.getSequence().substring((int) request.getStart(),
                                    (int) (long) request.getEnd()), nextPageToken
                            );
                }
            }
            it.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
