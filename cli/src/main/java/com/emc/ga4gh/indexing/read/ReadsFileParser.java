package com.emc.ga4gh.indexing.read;

import com.emc.ga4gh.DTO.Read;
import com.emc.ga4gh.indexing.Parser;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadsFileParser implements Parser<Read> {
	private File inputFile;

	public ReadsFileParser(File file) {
		this.inputFile = file;
	}

	@Override
	public List<Read> parse() {
		SamReader samReader = SamReaderFactory.make().open(inputFile);
		ArrayList<Read> reads = new ArrayList<>();
		int num = 0;
		for (SAMRecord samRecord : samReader) {
			Read read = new Read();
			read.setNumberInFile(num);
			read.setReadGroupId(samRecord.getReadGroup().getId());
			read.setAlignmentStart(samRecord.getAlignmentStart());
			read.setAlignmentEnd(samRecord.getAlignmentEnd());
//			GAReadAlignment alignment = parseSamRecord(samRecord);
//			read.setAlignmentSequence(alignment.getAlignedSequence());
			read.setReferenceId(samRecord.getReferenceIndex());
			read.setReferenceName(samRecord.getReferenceName());
			reads.add(read);
			num++;
		}
		return reads;
	}
//
//	private GAReadAlignment parseSamRecord(SAMRecord samRecord) {
//		Integer numberReads = null;
//		Integer readNumber = null;
//		GAPosition nextMatePosition = null;
//		Boolean properPlacement = null;
//		if (samRecord.getReadPairedFlag()) {
//			numberReads = 2;
//			properPlacement = samRecord.getProperPairFlag();
//			if (samRecord.getFirstOfPairFlag()) {
//				readNumber = 0;
//			}
//			readNumber = 1;
//			nextMatePosition = new GAPosition(
//					samRecord.getReferenceName(),
//					samRecord.getMateAlignmentStart(),
//					samRecord.getMateNegativeStrandFlag());
//		}
//
//		GALinearAlignment linAlignment = null;
//		if (!samRecord.getReadUnmappedFlag()) {
//			GAPosition position = new GAPosition(
//					samRecord.getReferenceName(),
//					samRecord.getReferencePositionAtReadPosition(0),
//					samRecord.getReadNegativeStrandFlag());
//			GACigarUnit[] cigar = new GACigarUnit[samRecord.getCigar()
//					.getCigarElements().size()];
//			for (int j = 0; j < cigar.length; j++) {
//				// null | string referenceSequence??
//				cigar[j] = new GACigarUnit(translate(samRecord
//						.getCigar().getCigarElement(j).getOperator()
//						.toString()), samRecord.getCigar()
//						.getCigarElement(j).getLength(), null);
//			}
//			linAlignment = new GALinearAlignment(position,
//					samRecord.getMappingQuality(), cigar);
//		}
//		Map<String, Object> info = new HashMap<>();
//		for (SAMTagAndValue pair : samRecord.getAttributes()) {
//			info.put(pair.tag, pair.value);
//		}
//
//		return new GAReadAlignment("", samRecord
//				.getReadGroup().getId(), samRecord.getReadName(),
//				properPlacement, samRecord.getDuplicateReadFlag(),
//				numberReads, samRecord.getReadLength(), readNumber,
//				samRecord.getReadFailsVendorQualityCheckFlag(),
//				linAlignment, samRecord.getNotPrimaryAlignmentFlag(),
//				samRecord.getSupplementaryAlignmentFlag(),
//				samRecord.getReadString(),
//				byteArrayToIntArray(samRecord.getBaseQualities()),
//				nextMatePosition, info);
//	}
//
//	private GACigarOperation translate(String SAMCigarOperationName) {
//		switch (SAMCigarOperationName) {
//			case "M":
//				return GACigarOperation.ALIGNMENT_MATCH;
//			case "I":
//				return GACigarOperation.INSERT;
//			case "D":
//				return GACigarOperation.DELETE;
//			case "N":
//				return GACigarOperation.SKIP;
//			case "S":
//				return GACigarOperation.CLIP_SOFT;
//			case "H":
//				return GACigarOperation.CLIP_HARD;
//			case "P":
//				return GACigarOperation.PAD;
//			case "=":
//				return GACigarOperation.SEQUENCE_MATCH;
//			case "X":
//				return GACigarOperation.SEQUENCE_MISMATCH;
//			default:
//				return GACigarOperation.ALIGNMENT_MATCH;
//		}
//	}
//
//	private int[] byteArrayToIntArray(byte[] byteArray) {
//		int[] intArray = new int[byteArray.length];
//		for (int i = 0; i < byteArray.length; i++) {
//			intArray[i] = byteArray[i];
//		}
//		return intArray;
//	}
}
