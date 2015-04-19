package com.emc.ga4gh.searching.searchVariants;

import com.emc.sk.ga4ghapi.model.GACall;
import com.emc.sk.ga4ghapi.model.GASearchVariantsRequest;
import com.emc.sk.ga4ghapi.model.GASearchVariantsResponse;
import com.emc.sk.ga4ghapi.model.GAVariant;
import com.google.gson.Gson;
import htsjdk.samtools.util.CloseableIterator;
import htsjdk.variant.variantcontext.Genotype;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileVariantsSearcher {

	private File inputFile;
	private ArrayList<String> callSetIndexes;
	private String variantSetId;
	private static String nextPageToken;
	private static final Integer DEFAULT_PAGESIZE = 5;
	
	public FileVariantsSearcher(File file , ArrayList<String> callSetIndexes, String variantSetId) {
		this.callSetIndexes = callSetIndexes;
		this.inputFile = file;
		this.variantSetId = variantSetId;
	}
	
	private String makeNextPageToken(GASearchVariantsRequest request, long offset) {
		GASearchVariantsRequest nextRequest = new GASearchVariantsRequest(
				request.getVariantSetIds(), request.getVariantName(),
				request.getCallSetIds(), request.getReferenceName(),
				request.getStart() + offset, request.getEnd(), request.getPageSize(), null);
		Gson gson = new Gson();
		return gson.toJson(nextRequest);
	}
	
	private GASearchVariantsRequest makeRequestFromToken(String pageToken) {
		GASearchVariantsRequest newRequest = new Gson().fromJson(pageToken,
				GASearchVariantsRequest.class);
		return newRequest;		
	}
	
	public GASearchVariantsResponse searchVariants(
			GASearchVariantsRequest request) {
		if (request.getPageToken() != null) {
			if (request.getPageToken().equals(nextPageToken)) {
				GASearchVariantsRequest reqFromToken = makeRequestFromToken(nextPageToken);
				request.setStart(reqFromToken.getStart());
				request.setEnd(reqFromToken.getEnd());
				request.setPageSize(reqFromToken.getPageSize());
				request.setVariantSetIds(reqFromToken.getVariantSetIds());
				request.setReferenceName(reqFromToken.getReferenceName());
				request.setCallSetIds(reqFromToken.getCallSetIds());
				request.setPageToken(null);
				request.setVariantName(reqFromToken.getVariantName());
				return searchVariants(request);
			}
		}
		ArrayList<GAVariant> variantList = new ArrayList<GAVariant>();
		VCFFileReader vcfReader = null;
		long offset = 0;
		if (new File(inputFile.getAbsolutePath() + ".gz.tbi").exists()) {
			vcfReader = new VCFFileReader(inputFile, new File(
					inputFile.getAbsolutePath() + ".gz.tbi"));
		} else {
			vcfReader = new VCFFileReader(inputFile, false);
		}
		String[] callSetIds = request.getCallSetIds();
		String variantName = request.getVariantName();
		String referenceName = request.getReferenceName();
		long start = request.getStart();
		long end = request.getEnd();
		Integer pageSize = request.getPageSize();
		int[] matchIndexes = new int[request.getCallSetIds().length];
		for (int i = 0; i < callSetIds.length; i++) {
			String id = callSetIds[i];
			int position = callSetIndexes.indexOf(id);
			if (position != -1) {
				matchIndexes[i] = position;
			} else {
				vcfReader.close();
				return new GASearchVariantsResponse();
			}
		}
		Arrays.sort(matchIndexes);
		CloseableIterator<VariantContext> iter = vcfReader.query(referenceName,
				(int) start, (int) end);
		int added = 0;
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		while (iter.hasNext()) {
			VariantContext variantContext = (VariantContext) iter.next();
			if (!variantContext.getChr().equals(referenceName)) {
				continue;
			}

			if (variantName != null) {
				if (!variantContext.getSampleNamesOrderedByName().contains(
						referenceName)) {
					continue;
				}
			}
			if ((variantContext.getStart() < start)
					|| (variantContext.getEnd() > end)
					|| (variantContext.getStart() > end)
					|| (variantContext.getEnd() < start)) {
				continue;
			}
			offset = variantContext.getStart() + 1;
			GAVariant variant = new GAVariant();
			variant.setId(variantContext.getID());

			String[] stockArr = new String[variantContext
					.getSampleNamesOrderedByName().size()];
			variant.setNames(variantContext.getSampleNamesOrderedByName()
					.toArray(stockArr));
			variant.setReferenceName(variantContext.getChr());
			variant.setStart(variantContext.getStart());
			variant.setEnd(variantContext.getEnd());
			variant.setVariantSetId(variantSetId);
			try {
				variant.setReferenceBases(new String(variantContext
						.getReference().getBases(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String[] alternateBases = new String[variantContext
					.getAlternateAlleles().size()];
			for (int i = 0; i < variantContext.getAlternateAlleles().size(); i++) {
				alternateBases[i] = variantContext.getAlternateAlleles().get(i)
						.getBaseString();
			}
			variant.setAlternateBases(alternateBases);
			variant.setInfo(variantContext.getAttributes());
			variant.setCreated(null);
			variant.setUpdated(inputFile.lastModified());

			GACall[] calls = new GACall[matchIndexes.length];
			if (matchIndexes.length != 0) {
				for (int i = 0; i < matchIndexes.length; i++) {
					int position = matchIndexes[i];
					Genotype genotype = variantContext.getGenotypes().get(
							position);
					GACall gaCall = new GACall(callSetIndexes.get(position),
							genotype.getSampleName(), genotype.getAD(),
							(String) genotype.getAnyAttribute("PS"), genotype
									.getLikelihoods().getAsVector(),
							genotype.getExtendedAttributes());
					calls[i] = gaCall;
				}
			} else {
				calls = new GACall[variantContext.getGenotypes().size()];
				for (int i = 0; i < variantContext.getGenotypes().size(); i++) {
					Genotype genotype = variantContext.getGenotypes().get(i);
					GACall gaCall = new GACall(null, genotype.getSampleName(),
							genotype.getAD(),
							(String) genotype.getAnyAttribute("PS"), genotype
									.getLikelihoods().getAsVector(),
							genotype.getExtendedAttributes());
					calls[i] = gaCall;
				}
			}
			variant.setCalls(calls);
			if (added < pageSize) {
				variantList.add(variant);
				++added;
			} else {
				iter.close();
				break;
			}
		}
		vcfReader.close();
		if (variantList.size() == 0) {
			nextPageToken = null;
		}
		else {
		nextPageToken = makeNextPageToken(request, offset);
		}
		return new GASearchVariantsResponse(
				variantList.toArray(new GAVariant[variantList.size()]),
				nextPageToken);
	}

}
