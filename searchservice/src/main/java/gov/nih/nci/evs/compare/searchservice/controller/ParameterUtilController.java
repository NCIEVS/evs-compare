package gov.nih.nci.evs.compare.searchservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.nih.nci.evs.compare.searchservice.model.RestPropertyMetadata;
import gov.nih.nci.evs.compare.searchservice.service.ParameterUtilityService;

@RestController
public class ParameterUtilController {
	
	@Autowired
	ParameterUtilityService service;
	
	@GetMapping("/statuslist")
	  public List<String> searchStatuslist() {
			return service.getConceptStatus();
	  }
	
	@GetMapping("/defsourcelist")
	  public List<RestPropertyMetadata> searchDefSourcelist() {
			return service.getDefSource();
	  }
	
	@GetMapping("/deftypelist")
	  public List<RestPropertyMetadata> searchdDefTypelist() {
			return service.getDefType();
	  }
	
	@GetMapping("/propertylist")
	  public List<RestPropertyMetadata> propertylist() {
			return service.getproperties();
	  }
	
	@GetMapping("/synsourcelist")
	  public List<RestPropertyMetadata> synonymSourcelist() {
			return service.getSynonymSource();
	  }
	
	@GetMapping("/syntermtypelist")
	  public List<RestPropertyMetadata> synTermTypeList() {
			return service.getSynonymTermType();
	  }
}
