package gov.nih.nci.evs.compare.searchservice.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nih.nci.evs.compare.searchservice.model.RestPropertyMetadata;
import gov.nih.nci.evs.compare.searchservice.util.SemanticTypeWrapper;

@Service
public class ParameterUtilityService {
	
	@Autowired
	EVSAPIBaseService service;
	
	
	public List<String> getConceptStatus(){
		List<String> entities = service.getRestConceptStatus();
		return entities;
	}


	public List<RestPropertyMetadata> getDefSource() {
		List<RestPropertyMetadata> entities = service.getRestDefSource();
		return entities;
	}


	public List<RestPropertyMetadata> getDefType() {
		List<RestPropertyMetadata> entities = service.getRestDefType();
		return entities;
	}


	public List<RestPropertyMetadata> getproperties() {
		List<RestPropertyMetadata> entities = service.getRestProperties();
		return entities;
	}


	public List<RestPropertyMetadata> getSynonymSource() {
		List<RestPropertyMetadata> entities = service.getRestSynSource();
		return entities;
	}


	public List<RestPropertyMetadata> getSynonymTermType() {
		List<RestPropertyMetadata> entities = service.getRestSynType();
		return entities;
	}


	public List<String> getQueryType() {
		List<String> entities = service.getRestQueryType();
		return entities;
	}
	
	public List<String> getSemanticTypes() {
		List<String> entities = Stream.of(new SemanticTypeWrapper().getSemanticTypes()).collect(Collectors.toList());
		return entities;
	}


}
