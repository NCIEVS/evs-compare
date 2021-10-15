package gov.nih.nci.evs.compare.searchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nih.nci.evs.compare.searchservice.model.ConceptWrapper;
import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;


@Service
public class TermSearchService {
	
	@Autowired
	EVSAPIBaseService service;
	
	
	public ConceptWrapper getConcepts(String term){
		ConceptWrapper entities = service.getConcepts(term);
		return entities;
	}


	public RestEntityWrapper getRestEntityInclusions(String term, String include) {
		RestEntityWrapper entities = service.getConceptsByInclusion(term, include);
		return entities;
	}
	
	public RestEntityWrapper getRestEntityInclusionsByType(String term, String include, String type) {
		RestEntityWrapper entities = service.getConceptsByInclusionAndType(term, include, type);
		return entities;
	}
}
