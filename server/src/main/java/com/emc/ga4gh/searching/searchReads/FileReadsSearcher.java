package com.emc.ga4gh.searching.searchReads;

import com.emc.sk.ga4ghapi.model.*;
import com.google.gson.Gson;
import htsjdk.samtools.SAMException;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SAMRecord.SAMTagAndValue;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import htsjdk.samtools.util.CloseableIterator;
import net.sf.picard.sam.BuildBamIndex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileReadsSearcher {
	
	private File inputFile; 	
	private File bamIndexFile;
	private final String INDEX_FILE_EXTENTION = ".bai";
	private static final Integer DEFAULT_PAGESIZE = 5;
	private static String nextPageToken;

	private int[] byteArrayToIntArray(byte[] byteArray) {
		int[] intArray = new int[byteArray.length];
		for (int i = 0; i < byteArray.length; i++) {
			intArray[i] = byteArray[i];
		}
		return intArray;
	}
	
	private String makeNextPageToken(GASearchReadsRequest request, long offset) {
		GASearchReadsRequest nextRequest = new GASearchReadsRequest(
				request.getReadGroupIds(), request.getReferenceName(),
				request.getReferenceId(), request.getStart() + offset,
				request.getEnd(), request.getPageSize(), null);
		Gson gson = new Gson();
		return gson.toJson(nextRequest);
	}
	
	private GASearchReadsRequest makeRequestFromToken(String pageToken) {
		GASearchReadsRequest newRequest = new Gson().fromJson(pageToken,
				GASearchReadsRequest.class);
		return newRequest;		
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
		for (SAMTagAndValue pair : samRecord.getAttributes()) {
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
	
	public GACigarOperation translate(String SAMCigarOperationName) {
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
	
	public FileReadsSearcher(File file) {
		this.inputFile = file;
		bamIndexFile = new File(inputFile.getAbsolutePath()
				+ INDEX_FILE_EXTENTION);
		if (!bamIndexFile.exists()) {
			try {
				new BuildBamIndex().instanceMain(new String[] {
						"I=" + inputFile.getAbsolutePath(),
						"O=" + bamIndexFile.getAbsolutePath(),
						"MAX_RECORDS_IN_RAM=50000",
						"VALIDATION_STRINGENCY=SILENT" });
			} catch (SAMException e) {
				e.printStackTrace();
			}
		}
	}

	public GASearchReadsResponse searchReads(GASearchReadsRequest request) {
		if (request.getPageToken() != null) {
			if (request.getPageToken().equals(nextPageToken)) {
				GASearchReadsRequest reqFromToken = makeRequestFromToken(nextPageToken);
				request.setStart(reqFromToken.getStart());
				request.setEnd(reqFromToken.getEnd());
				request.setPageSize(reqFromToken.getPageSize());
				request.setReferenceId(reqFromToken.getReferenceId());
				request.setReferenceName(reqFromToken.getReferenceName());
				request.setReadGroupIds(reqFromToken.getReadGroupIds());	
				request.setPageToken(null);
				return searchReads(request);
			}
		}
		SamReader samReader = SamReaderFactory.make().open(inputFile);
		ArrayList<GAReadAlignment> responceAlignments = new ArrayList<GAReadAlignment>();
		String[] readGroupIds = request.getReadGroupIds();
		String referenceId = request.getReferenceId();
		String referenceName = request.getReferenceName();
		Long start = request.getStart();
		Long end = request.getEnd();
		Integer pageSize = request.getPageSize();
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		int added = 0;
		long offset = 0;
		if ((referenceName == null) && (referenceId == null)) {
			for (SAMRecord samRecord : samReader) {
				++offset;
				if (readGroupIds.length != 0) {
					if (!Arrays.asList(readGroupIds).contains(
							samRecord.getReadGroup().getId())) {
						continue;
					}
				}
				if ((start != null) && (end != null)) {
					if ((samRecord.getAlignmentStart() < start)
							|| (samRecord.getAlignmentStart() > end)
							|| (samRecord.getAlignmentEnd() > end)
							|| (samRecord.getAlignmentEnd() < start))
						continue;
				}
				GAReadAlignment alignment = parseSamRecord(samRecord);
				++added;
				responceAlignments.add(alignment);
				if (added == pageSize) {
					try {
						samReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}

		} else {
			String ref = null;
			if (referenceName == null) {
				ref = referenceId;
			} else {
				ref = referenceName;
			}
			final CloseableIterator<SAMRecord> it;
			if (start == null) {
				start = (long) 0;
			}
			if (end == null) {
				end = (long) 0;
				it = samReader.queryAlignmentStart(ref, (int) (long) start);
			} else {
				it = samReader.queryContained(ref, (int) (long) start,
						(int) (long) end);
			}
			while (it.hasNext()) {
				++offset;
				SAMRecord samRecord = it.next();
				if (readGroupIds.length != 0) {
					if (Arrays.asList(readGroupIds).contains(
							samRecord.getReadGroup().getId())) {
						++added;
						responceAlignments.add(parseSamRecord(samRecord));
					}
				} else {
					++added;
					responceAlignments.add(parseSamRecord(samRecord));
				}
				if (added == pageSize) {
					it.close();
					break;
				}
			}
			it.close();
		}
		try {
			samReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		nextPageToken = makeNextPageToken(request, offset);
		return new GASearchReadsResponse(
				responceAlignments.toArray(new GAReadAlignment[responceAlignments
						.size()]), nextPageToken);
	}

}
