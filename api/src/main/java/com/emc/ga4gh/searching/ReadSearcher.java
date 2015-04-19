package com.emc.ga4gh.searching;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.model.*;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import htsjdk.samtools.util.CloseableIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by liza on 15.04.15.
 */

@Component
public class ReadSearcher implements Searcher<GASearchReadsResponse, GASearchReadsRequest> {

    @Autowired
    ReadDAO rd;

    private File inputFile;
    private static final Integer DEFAULT_PAGESIZE = 5;

    public ReadSearcher(File file) {
        this.inputFile = file;
    }

    @Override
    public GASearchReadsResponse search(GASearchReadsRequest request) {
        SamReader samReader = SamReaderFactory.make().open(inputFile);
        ArrayList<GAReadAlignment> responseAlignments = new ArrayList<>();

        List<String> readGroupIds = Arrays.asList(request.getReadGroupIds());
        String referenceId = request.getReferenceId();
        String referenceName = request.getReferenceName();
        String ref;
        if (referenceId == null) {
            ref = referenceId;
        } else {
            ref = referenceName;
        }

        Long start = request.getStart();
        if (start == null) {
            start = (long) 0;
        }
        Long end = request.getEnd();

        List<Read> reads = rd.findIncOrdered(referenceId, referenceName, start, end, readGroupIds);

        long offset = 0;
        for (Read read : reads) {
            int nextReadsNumberInFile = read.getNumberInFile();
            for (SAMRecord samRecord : samReader) {
                if (offset == nextReadsNumberInFile) {
                    GAReadAlignment alignment = parseSamRecord(samRecord);
                    responseAlignments.add(alignment);
                }
                ++offset;
            }
        }

        try {
            samReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GASearchReadsResponse(
                responseAlignments.toArray(new GAReadAlignment[responseAlignments
                        .size()]), "");
    }



    private int[] byteArrayToIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            intArray[i] = byteArray[i];
        }
        return intArray;
    }

    private GAReadAlignment parseSamRecord(SAMRecord samRecord) {
        Integer numberReads = null;
        Integer readNumber = null;
        GAPosition nextMatePosition = null;
        Boolean properPlacement = null;
        if (samRecord.getReadPairedFlag()) {
            numberReads = 2;
            properPlacement = samRecord.getProperPairFlag();
            if (samRecord.getFirstOfPairFlag()) {
                readNumber = 0;
            }
            readNumber = 1;
            nextMatePosition = new GAPosition(
                    samRecord.getReferenceName(),
                    samRecord.getMateAlignmentStart(),
                    samRecord.getMateNegativeStrandFlag());
        }

        GALinearAlignment linAlignment = null;
        if (!samRecord.getReadUnmappedFlag()) {
            GAPosition position = new GAPosition(
                    samRecord.getReferenceName(),
                    samRecord.getReferencePositionAtReadPosition(0),
                    samRecord.getReadNegativeStrandFlag());
            GACigarUnit[] cigar = new GACigarUnit[samRecord.getCigar()
                    .getCigarElements().size()];
            for (int j = 0; j < cigar.length; j++) {
                // null | string referenceSequence??
                cigar[j] = new GACigarUnit(translate(samRecord
                        .getCigar().getCigarElement(j).getOperator()
                        .toString()), samRecord.getCigar()
                        .getCigarElement(j).getLength(), null);
            }
            linAlignment = new GALinearAlignment(position,
                    samRecord.getMappingQuality(), cigar);
        }
        Map<String, Object> info = new HashMap<String, Object>();
        for (SAMRecord.SAMTagAndValue pair : samRecord.getAttributes()) {
            info.put(pair.tag, pair.value);
        }

        GAReadAlignment alignment = new GAReadAlignment("", samRecord
                .getReadGroup().getId(), samRecord.getReadName(),
                properPlacement, samRecord.getDuplicateReadFlag(),
                numberReads, samRecord.getReadLength(), readNumber,
                samRecord.getReadFailsVendorQualityCheckFlag(),
                linAlignment, samRecord.getNotPrimaryAlignmentFlag(),
                samRecord.getSupplementaryAlignmentFlag(),
                samRecord.getReadString(),
                byteArrayToIntArray(samRecord.getBaseQualities()),
                nextMatePosition, info);
        return alignment;
    }

    private GACigarOperation translate(String SAMCigarOperationName) {
        switch (SAMCigarOperationName) {
            case "M":
                return GACigarOperation.ALIGNMENT_MATCH;
            case "I":
                return GACigarOperation.INSERT;
            case "D":
                return GACigarOperation.DELETE;
            case "N":
                return GACigarOperation.SKIP;
            case "S":
                return GACigarOperation.CLIP_SOFT;
            case "H":
                return GACigarOperation.CLIP_HARD;
            case "P":
                return GACigarOperation.PAD;
            case "=":
                return GACigarOperation.SEQUENCE_MATCH;
            case "X":
                return GACigarOperation.SEQUENCE_MISMATCH;
            default:
                return GACigarOperation.ALIGNMENT_MATCH;
        }
    }
}