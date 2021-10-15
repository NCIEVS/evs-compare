package gov.nih.nci.evs.compare.searchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.nih.nci.evs.compare.searchservice.model.ConceptWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;
import gov.nih.nci.evs.compare.searchservice.service.TermSearchService;


@RestController
public class BasicContainsEntitySearchController {
	
	@Autowired
	TermSearchService service;

	@GetMapping("/search/{term}")
	  public ConceptWrapper searchTerm(@PathVariable String term) {
			return service.getConcepts(
					term);
	  }
	
	@GetMapping("/search/{term}/{include}")
	  public RestEntityWrapper searchTermWithInclusions(@PathVariable String term, @PathVariable String include) {
			return service.getRestEntityInclusions(
					term, include);
	  }
	
	@GetMapping("/search/{include}/{type}/{term}")
	  public RestEntityWrapper searchTermWithInclusionsAndType(@PathVariable String term, @PathVariable String include, @PathVariable String type) {
			return service.getRestEntityInclusionsByType(
					term, include, type);
	  }

}
