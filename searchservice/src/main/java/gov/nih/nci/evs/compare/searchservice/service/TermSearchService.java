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
	
	public RestEntityWrapper getRestEntityInclusionsByTypeSourcePropsIncludes(String source, String props,String include,String querytype, String terms, String start, String size) {
		RestEntityWrapper entities = service.getConceptsBySourcePropsInclusionType(source, props, include, querytype, terms, start, size);
		return entities;
	}


	public RestEntityWrapper getRestEntityInclusionsByTypeSourceIncludes(String source, String includes, String queryType,
			String terms, String recordStart, String pageSize) {
		RestEntityWrapper entities = service.getConceptsBySourceInclusionType(source, includes, queryType, terms, recordStart, pageSize);
		return entities;
	}
}
