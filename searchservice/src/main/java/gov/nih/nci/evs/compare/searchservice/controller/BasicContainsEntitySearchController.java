package gov.nih.nci.evs.compare.searchservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.nih.nci.evs.compare.searchservice.model.Concept;
import gov.nih.nci.evs.compare.searchservice.model.ConceptWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestEntity;
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

}
