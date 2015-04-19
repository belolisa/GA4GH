package com.emc.ga4gh.searching.searchReferences;

import com.google.gson.Gson;
import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.FASTAFileReader;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by timofb on 4/6/2015.
 */
public class FileReferenceSearcher {

    private File inputFile;
    private long temporaryOffset = 0;
    private static final Integer DEFAULT_PAGESIZE = 5;
    private static String nextPageToken;

    public FileReferenceSearcher(File file) {
        this.inputFile = file;
    }

    private String makeNextPageToken(GASearchReferencesRequest request,
                                     long offset) {
        ReferencesPageToken token = new ReferencesPageToken(
                request.getAccessions(),
                request.getPageSize(), offset, request.getMd5checksums());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

    private GASearchReferencesRequest makeRequestFromToken(String pageToken) {
        ReferencesPageToken token = new Gson().fromJson(pageToken,
                ReferencesPageToken.class);
        GASearchReferencesRequest newRequest = new GASearchReferencesRequest(token.getMd5checksums(),
                token.getAccessions(),
                token.getPageSize(), null);
        temporaryOffset =  token.getOffset();
        return newRequest;
    }

    public GASearchReferencesResponse searchReferences(GASearchReferencesRequest request) {
        if (request.getPageToken() != null) {
            if (request.getPageToken().equals(nextPageToken)) {
                GASearchReferencesRequest reqFromToken = makeRequestFromToken(nextPageToken);
                request.setAccessions(reqFromToken.getAccessions());
                request.setMd5checksums(reqFromToken.getMd5checksums());
                request.setPageSize(reqFromToken.getPageSize());
                request.setPageToken(reqFromToken.getPageToken());
                return searchReferences(request);
            }
        }
        int added = 0;
        long offset  = -1;
        Integer pageSize = request.getPageSize();
        ArrayList<GAReference> references = new ArrayList<GAReference>();
        if (pageSize == null) {
            pageSize = DEFAULT_PAGESIZE;
        }
        FASTAFileReader reader;
        try {
            reader = new FASTAFileReaderImpl(inputFile);
            final FASTAElementIterator it = reader.getIterator();
            while (it.hasNext()) {
                ++offset;
                if (offset < temporaryOffset) {
                    continue;
                }
                GAReference reference = new GAReference();
                final FASTAElement el = it.next();
                String header = el.getHeader();
                String fileReferenceId = header.substring(0, header.indexOf(" "));
                reference.setId(fileReferenceId);
                reference.setLength(el.getSequenceLength());
                ++added;
                references.add(reference);
                if (added == pageSize) {
                    it.close();
                    reader.close();
                }
            }
            it.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (references.size() != 0) {
            nextPageToken = makeNextPageToken(request, offset);
        }
        else {
            nextPageToken = null;
        }
        return new GASearchReferencesResponse(references.toArray(new GAReference[references.size()]), nextPageToken);
    }


}
