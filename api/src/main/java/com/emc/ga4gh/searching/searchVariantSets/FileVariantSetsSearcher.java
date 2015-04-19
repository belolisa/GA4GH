package com.emc.ga4gh.searching.searchVariantSets;

import com.emc.sk.ga4ghapi.fileStorage.IdGenerator;
import com.emc.sk.ga4ghapi.model.GASearchVariantSetsRequest;
import com.emc.sk.ga4ghapi.model.GAVariantSet;
import com.emc.sk.ga4ghapi.model.GAVariantSetMetadata;
import htsjdk.variant.vcf.VCFFileReader;
import htsjdk.variant.vcf.VCFFilterHeaderLine;
import htsjdk.variant.vcf.VCFFormatHeaderLine;
import htsjdk.variant.vcf.VCFInfoHeaderLine;

import java.io.File;
import java.util.*;

public class FileVariantSetsSearcher {

	private File inputFile;
	
	private String variantSetId;
	
	public FileVariantSetsSearcher(File file, String variantSetId) {
		this.inputFile = file;
		this.variantSetId = variantSetId;
	}

	public GAVariantSet searchVariantSets(GASearchVariantSetsRequest request) {
		VCFFileReader vcfReader = new VCFFileReader(inputFile, false);
		String id = "";
		if (variantSetId != null) {
			id = variantSetId;
		}
		else {
			 id = IdGenerator.generateId();
		}
		ArrayList<GAVariantSetMetadata> metadata= new ArrayList<GAVariantSetMetadata>();
		Iterator<VCFInfoHeaderLine> infoIter = vcfReader.getFileHeader().getInfoHeaderLines().iterator();
		while (infoIter.hasNext()) {
			GAVariantSetMetadata metadataLine = new GAVariantSetMetadata();
			VCFInfoHeaderLine line = (VCFInfoHeaderLine) infoIter.next(); 
			metadataLine.setKey(line.getKey());
			metadataLine.setValue(line.getValue());
			metadataLine.setType(line.getType().name());
			metadataLine.setDescription(line.getDescription());
			metadataLine.setNumber(line.getID());
			metadataLine.setId(IdGenerator.generateId());
			metadataLine.setInfo(new HashMap<String, Object>());
			metadata.add(metadataLine);
		}
		List<VCFFilterHeaderLine> filterLines= vcfReader.getFileHeader().getFilterLines();
		for (VCFFilterHeaderLine line : filterLines) {
			GAVariantSetMetadata metadataLine = new GAVariantSetMetadata();
			metadataLine.setKey(line.getKey());
			metadataLine.setValue(line.getValue());
			//metadataLine.setType(line.g);
			//metadataLine.setDescription(line.getDescription());
			metadataLine.setNumber(line.getID());
			metadataLine.setId(IdGenerator.generateId());
			metadataLine.setInfo(new HashMap<String, Object>());
			metadata.add(metadataLine);
		}
		Collection<VCFFormatHeaderLine> formatLines = vcfReader.getFileHeader().getFormatHeaderLines();
		for (VCFFormatHeaderLine line : formatLines) {
			GAVariantSetMetadata metadataLine = new GAVariantSetMetadata();
			metadataLine.setKey(line.getKey());
			metadataLine.setValue(line.getValue());
			metadataLine.setType(line.getType().name());
			metadataLine.setDescription(line.getDescription());
			metadataLine.setNumber(line.getID());
			metadataLine.setId(IdGenerator.generateId());
			metadataLine.setInfo(new HashMap<String, Object>());
			metadata.add(metadataLine);
		}
		/*Collection<VCFIDHeaderLine> idLines = vcfReader.getFileHeader().getIDHeaderLines();
		for (VCFIDHeaderLine line : idLines) {
			GAVariantSetMetadata metadataLine = new GAVariantSetMetadata();
			metadataLine.setKey(line.getKey());
			metadataLine.setValue(line.getValue());
			metadataLine.setType(line.getType().name());
			metadataLine.setDescription(line.getDescription());
			metadataLine.setNumber(line.getID());
			metadataLine.setId(IdGenerator.generateId());
			metadataLine.setInfo(new HashMap<String, Object>());
		}*/	
		GAVariantSet set = new GAVariantSet(id, "", metadata.toArray(new GAVariantSetMetadata[metadata.size()]));
		vcfReader.close();
		return set;
	}
	

}
