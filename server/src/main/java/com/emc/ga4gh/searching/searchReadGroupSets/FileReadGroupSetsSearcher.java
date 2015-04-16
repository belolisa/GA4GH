package com.emc.ga4gh.searching.searchReadGroupSets;

import com.emc.sk.ga4ghapi.fileStorage.IdGenerator;
import com.emc.sk.ga4ghapi.model.*;
import htsjdk.samtools.SAMProgramRecord;
import htsjdk.samtools.SAMReadGroupRecord;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileReadGroupSetsSearcher {
	private File inputFile;
	private String readGroupSetId = "";
	
	public FileReadGroupSetsSearcher(File file) {
		this.inputFile = file;
	}
	
	public FileReadGroupSetsSearcher(File file, String id) {
		this.inputFile = file;
		this.readGroupSetId = id;
	}
	
	public GAReadGroupSet searchReadGroupSets(GASearchReadGroupSetsRequest request) {
		if (readGroupSetId == "") {
			readGroupSetId = IdGenerator.generateId();
		}
		String readGroupSetDataSetId = null;
		String readGroupSetName = inputFile.getName();
		SamReader samReader = SamReaderFactory.make().open(inputFile);
		GAReadGroup[] readGroups = new GAReadGroup[samReader.getFileHeader().getReadGroups().size()];
		for (int i = 0; i < samReader.getFileHeader().getReadGroups().size(); ++i) {
			SAMReadGroupRecord record = samReader.getFileHeader()
					.getReadGroups().get(i);
			String readGroupId = record.getId();
			String readGroupDataSetId = readGroupSetDataSetId;
			String readGroupName = null;
			String description = record.getDescription();
			String sampleId = record.getSample();
			GAExperiment experiment = new GAExperiment(record.getLibrary(),
					record.getPlatformUnit(), record.getSequencingCenter(),
					record.getPlatform());
			Integer predictedInsertSize = record.getPredictedMedianInsertSize();
			Long created;
			if (record.getRunDate() != null) {
				created = record.getRunDate().getTime();
			}
			else {
				created = null;
			}
			Long updated = null;
			GAProgram[] programs = new GAProgram[samReader.getFileHeader()
					.getProgramRecords().size()];
			for (int j = 0; j < samReader.getFileHeader().getProgramRecords()
					.size(); ++j) {
				SAMProgramRecord programRecord = samReader.getFileHeader()
						.getProgramRecords().get(j);
				programs[j] = new GAProgram(programRecord.getCommandLine(),
						programRecord.getId(), programRecord.getProgramName(),
						programRecord.getPreviousProgramGroupId(),
						programRecord.getProgramVersion());
			}
			String referenceSetId = null;
			Map<String, Object> info = new HashMap<String, Object>();
			for (Entry<String, String> entry: record.getAttributes()) {
				info.put(entry.getKey(), entry.getValue());
			}
			readGroups[i] = new GAReadGroup(readGroupId, readGroupDataSetId, readGroupName,
					description, sampleId, experiment, predictedInsertSize, created, updated, programs, referenceSetId, info);
		}
		try {
			samReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GAReadGroupSet(readGroupSetId, readGroupSetDataSetId, readGroupSetName, readGroups);
	}

}
