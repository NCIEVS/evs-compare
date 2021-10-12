package gov.nih.nci.evs.compare.searchservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nih.nci.evs.compare.searchservice.model.Concept;
import gov.nih.nci.evs.compare.searchservice.model.ConceptWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestEntity;


@Service
public class TermSearchService {
	
	@Autowired
	EVSAPIBaseService service;
	
	
	public ConceptWrapper getConcepts(String term){
		ConceptWrapper entities = service.getConcepts(term);
		return entities;
	}
}
