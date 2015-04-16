package com.emc.ga4gh.controller;

import com.emc.ga4gh.model.*;
import com.emc.ga4gh.server.Server;
import com.emc.ga4gh.spring.aop.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@Autowired
	private Server server;

	@Log
	@RequestMapping(value = "/references/{id}", method = RequestMethod.GET)
	public @ResponseBody GAReference getReference(@PathVariable("id") String id) {
		return server.getReference(id);
	}

	@Log
	@RequestMapping(value = "/references/{id}/bases", method = RequestMethod.GET)
	public @ResponseBody GAListReferenceBasesResponse getReferenceBases(
			@PathVariable("id") String id,
			@RequestParam(value = "start", defaultValue="0") long start,
			@RequestParam(value = "end", defaultValue="null") Long end,
			@RequestParam(value = "pageToken", defaultValue="null") String pageToken) {
		return server.getReferenceBases(id, new GAListReferenceBasesRequest(start, end, pageToken));
	}

	@Log
	@RequestMapping(value = "/referencesets/{id}", method = RequestMethod.GET)
	public @ResponseBody
	GAReferenceSet getReferenceSet(@PathVariable("id") String id) {
		return server.getReferenceSet(id);
	}
	
	@Log @RequestMapping(value = "/callsets/search", method = RequestMethod.POST)
	public 	@ResponseBody GASearchCallSetsResponse searchCallSets(@RequestBody GASearchCallSetsRequest request) {
		return server.searchCallSets(request);
	}  
	
	@Log @RequestMapping(value = "/readgroupsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadGroupSetsResponse searchReadGroupSets(@RequestBody GASearchReadGroupSetsRequest request) {
		return server.searchReadGroupSets(request);
	}	
	
	@Log @RequestMapping(value = "/reads/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReadsResponse searchReads(@RequestBody GASearchReadsRequest request) {	
		return server.searchReads(request);
	}	
	
	@Log @RequestMapping(value = "/referencesets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferenceSetsResponse searchReferenceSets(@RequestBody GASearchReferenceSetsRequest request) {
		return server.searchReferenceSets(request);
	}	
	
	@Log @RequestMapping(value = "/references/search", method = RequestMethod.POST)
	public @ResponseBody GASearchReferencesResponse searchReferences(@RequestBody GASearchReferencesRequest request) {	
		return server.searchReferences(request);
	}	
	
	@Log @RequestMapping(value = "/variantsets/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantSetsResponse searchVariantSets(@RequestBody GASearchVariantSetsRequest request) {
		return server.searchVariantSets(request);
	}	
	
	@Log @RequestMapping(value = "/variants/search", method = RequestMethod.POST)
	public @ResponseBody GASearchVariantsResponse searchVariants(@RequestBody GASearchVariantsRequest request) {
		return server.searchVariants(request);
	}	
}
