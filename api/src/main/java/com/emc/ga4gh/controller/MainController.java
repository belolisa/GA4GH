package com.emc.ga4gh.controller;

import com.emc.ga4gh.model.*;
import com.emc.ga4gh.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class MainController {

	@Autowired
	private Server server;

    private static Logger log = Logger.getLogger(MainController.class.getName());
	
	@RequestMapping(value = "/references/{id}", method = RequestMethod.GET)
	public @ResponseBody GAReference getReference(@PathVariable("id") String id) {
		log.info("Processing /references/{id}");
		return server.getReference(id);
	}	
	
	@RequestMapping(value = "/references/{id}/bases", method = RequestMethod.GET)
	public @ResponseBody GAListReferenceBasesResponse getReferenceBases(
			@PathVariable("id") String id,
			@RequestParam(value = "start", defaultValue="0") long start,
			@RequestParam(value = "end", defaultValue="null") Long end,
			@RequestParam(value = "pageToken", defaultValue="null") String pageToken) {
		log.info("Processing references/{id}/bases");
		return server.getReferenceBases(id, new GAListReferenceBasesRequest(start, end, pageToken));
	}	
	
	@RequestMapping(value = "/referencesets/{id}", method = RequestMethod.GET)
	public @ResponseBody
	GAReferenceSet getReferenceSet(@PathVariable("id") String id) {
		log.info("Processing references/{id}");
		return server.getReferenceSet(id);
	}
	
	@RequestMapping(value = "/callsets/search", method = RequestMethod.POST)
	public 	@ResponseBody GASearchCallSetsResponse searchCallSets(@RequestBody GASearchCallSetsRequest request) {
		log.info("Processing /callsets/search");
		return server.searchCallSets(request);
	}  
	
	@RequestMapping(value = "/readgroupsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadGroupSetsResponse searchReadGroupSets(@RequestBody GASearchReadGroupSetsRequest request) {
		log.info("Processing /readgroupsets/search");
		return server.searchReadGroupSets(request);
	}	
	
	@RequestMapping(value = "/reads/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadsResponse searchReads(@RequestBody GASearchReadsRequest request) {	
		log.info("Processing /reads/search");
		return server.searchReads(request);
	}	
	
	@RequestMapping(value = "/referencesets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferenceSetsResponse searchReferenceSets(@RequestBody GASearchReferenceSetsRequest request) {
		log.info("Processing /referencesets/search");
		return server.searchReferenceSets(request);
	}	
	
	@RequestMapping(value = "/references/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferencesResponse searchReferences(@RequestBody GASearchReferencesRequest request) {	
		log.info("Processing /references/search");
		return server.searchReferences(request);
	}	
	
	@RequestMapping(value = "/variantsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantSetsResponse searchVariantSets(@RequestBody GASearchVariantSetsRequest request) {
		log.info("Processing /variantsets/search");	
		return server.searchVariantSets(request);
	}	
	
	@RequestMapping(value = "/variants/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantsResponse searchVariants(@RequestBody GASearchVariantsRequest request) {
		log.info("Processing /variants/search");	
		return server.searchVariants(request);
	}	
}
