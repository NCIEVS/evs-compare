package gov.nih.nci.evs.compare.searchservice.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;
import gov.nih.nci.evs.compare.searchservice.util.CommonServices;
import gov.nih.nci.evs.compare.searchservice.util.TabDelUtility;

@Service
public class FormattedOutputService {
	
	@Autowired
	EVSAPIBaseService service;
	
	
	@Autowired
	TermSearchService tsservice;
	


	
	public InputStream produceMinTabDelOutputFromList(
			String terms, String props, String queryType, String recordStart, String pageSize) {
		List<RestEntityWrapper> reWrappers =  CommonServices
				.splitInput(terms)
				.stream()
				.map(x ->
				tsservice.getRestEntityInclusionsByType(x, "minimal", queryType))
				.collect(Collectors.toList());

		return new ByteArrayInputStream(new TabDelUtility()
				.produceMinTabDelOutputFromListWithHeading(reWrappers, terms, props, queryType, recordStart, pageSize).getBytes());
	}

	
}
