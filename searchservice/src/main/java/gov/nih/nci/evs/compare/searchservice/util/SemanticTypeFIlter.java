package gov.nih.nci.evs.compare.searchservice.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import gov.nih.nci.evs.compare.searchservice.model.Property;
import gov.nih.nci.evs.compare.searchservice.model.RestEntity;

public class SemanticTypeFIlter {
	
	@Value("${REST_SEMANTICTYPE}")
	private String SEMANTIC_TYPE;
	
	public boolean isSemanticType(String type) {
		return type.equals(SEMANTIC_TYPE);
	}
	
	public boolean isSemanticTypeThis(String semanticType, RestEntity entity) {
		return semanticType.equals(SEMANTIC_TYPE);
	}
	
	
	public List<RestEntity> filterEntities(List<RestEntity> entities, String filterValue){
		return entities.stream().filter(x -> entityHasSemanticType(x, filterValue)).collect(Collectors.toList());
	}
	
	public boolean entityHasSemanticType(RestEntity entity, String filterValue) {
		return entity.getProperties()
				.stream()
				.anyMatch(x -> isSemanticType(x.getType()) && isMatchSemanticType(x, filterValue))
				 ;
	}
	
	public boolean isMatchSemanticType(Property prop, String value) {
		return prop.getValue().equals(value);
	}

}
