package gov.nih.nci.evs.compare.searchservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	  public List<String> searchDefSourcelist() {
		return service.getDefSource().stream().map(x -> x.toString()).collect(Collectors.toList());
	  }
	
	@GetMapping("/deftypelist")
	  public List<String> searchdDefTypelist() {
		return service.getDefType().stream().map(x -> x.toString()).collect(Collectors.toList());
		//	return service.getDefType();
	  }
	
	@GetMapping("/propertylist")
	  public List<String> propertylist() {
			return service.getproperties().stream().map(x -> x.toString()).collect(Collectors.toList());
	  }
	
	@GetMapping("/synsourcelist")
	  public List<String> synonymSourcelist() {
			return service.getSynonymSource().stream().map(x -> x.toString()).collect(Collectors.toList());
	  }
	
	@GetMapping("/syntermtypelist")
	  public List<String> synTermTypeList() {
			return service.getSynonymTermType().stream().map(x -> x.toString()).collect(Collectors.toList());
	  }
	
	@GetMapping("/querytype")
	  public List<String> queryTypeList() {
			return service.getQueryType().stream().map(x -> x.toString()).collect(Collectors.toList());
	  }
	
	@GetMapping("/semantictypelist")
	  public List<String> semanticTypeList() {
			return service.getSemanticTypes();
	  }
	
	@GetMapping("/getentityscopetype")
	  public List<String> entityScopeList() {
			return service.getEntityScope();
	  }
	
	@GetMapping("/terminologies")
	  public List<String> terminologyList() {
			return service.getTerminologies();
	  }
	
}
