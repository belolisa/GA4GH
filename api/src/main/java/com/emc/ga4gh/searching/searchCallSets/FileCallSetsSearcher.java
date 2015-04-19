package com.emc.ga4gh.searching.searchCallSets;

import com.emc.ga4gh.model.GACallSet;
import com.emc.ga4gh.model.GASearchCallSetsRequest;
import htsjdk.variant.vcf.VCFFileReader;
import org.springframework.util.IdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class FileCallSetsSearcher {

	private File inputFile;
	private String[] callSetIds;
	private String variantSetId;

	public FileCallSetsSearcher(File file, String[] callSetIds, String variantSetId) {
		this.variantSetId = variantSetId;
		this.inputFile = file;
		this.callSetIds = callSetIds;
	}

	public String[] getCallSetIds() {
		return callSetIds;
	}

	public ArrayList<GACallSet> searchCallSets(GASearchCallSetsRequest request) {
		VCFFileReader vcfReader = new VCFFileReader(inputFile, false);
		ArrayList<GACallSet> sets = new ArrayList<GACallSet>();
		String requestCallSetName = request.getName();
		ArrayList<String> callSetIds = new ArrayList<String>();
		for (int i = 0; i < vcfReader.getFileHeader().getGenotypeSamples()
				.size(); i++) {
			String callSetId = null;
			if (this.callSetIds.length == 0) {
				//			TODO fix this
				callSetId = IdGenerator.generateId();
			}
			else {
				callSetId = this.callSetIds[i];
			}
			callSetIds.add(callSetId);
			String callSetName = vcfReader.getFileHeader().getGenotypeSamples()
					.get(i);
			if  ((requestCallSetName == null) || (callSetName
							.contains(requestCallSetName))) {
				String sampleId = "";
				Long created = null;
				Long updated = null;
				Map<String, Object> info = null;
				String[] variantSetIds = new String[1];
				variantSetIds[0] = variantSetId;
				GACallSet callSet = new GACallSet(callSetId, callSetName,
						sampleId, variantSetIds, created, updated, info);
				sets.add(callSet);
			}
		}
		this.callSetIds = callSetIds.toArray(new String[callSetIds.size()]);
		vcfReader.close();
		return sets;
	}

}
