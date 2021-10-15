package gov.nih.nci.evs.compare.searchservice.util;

import java.util.List;
import java.util.stream.Collectors;

import gov.nih.nci.evs.compare.searchservice.model.RestEntity;
import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;


public class TabDelUtility extends FormatUtility{

	public String produceTabDelOutputFromListWithHeading(
			List<RestEntity> list,String term, String props, String queryType, String recordStart, String pageSize)  {
		CommonServices services = new CommonServices();

		StringBuffer firstLine = new StringBuffer();
		String separator = "\t";
		StringBuffer oneLine = new StringBuffer();
		list.stream().forEach(x -> {x.getProperties()
			.stream()
			.forEach(z -> services.addPropertyTypeAndPositionToCache(z));
			oneLine.append(
				"\r\n" + x.getTerminology() + 
				separator + x.getCode() + 
				separator + x.getName() + 
				separator + CommonServices.cleanListOutPut(CommonServices.getListValues(x.getParents())) +
				services.fullyCuratedProperties(x.getSynonyms(), separator, CommonServices.SYNONYMS) + 
				services.fullyCuratedProperties(x.getDefinitions(), separator, CommonServices.DEFINITIONS) + 
				services.fullyCuratedProperties(x.getMaps(), separator, CommonServices.MAPS) + 
				separator + services.calculateAndProduceSpacedTerms(separator));

			    services.clearPropertyListsFromHeaderMap();});
		services.filterHeadings(services).stream()
		.forEach(x -> firstLine.append(x + separator));
		String firstHeaderString = CommonServices.cleanListOutPut(firstLine.toString());
		firstLine.replace(firstHeaderString.lastIndexOf(separator), firstHeaderString.length(), "");
		String secondHeader = services.getHeadersByPosition(
				services.getPropHeaderMap())
						.stream()
						.collect(Collectors.joining(separator));
		oneLine.insert(0, firstHeaderString + secondHeader);
		oneLine.append(produceDelimitedQueryRecord(separator, term, props, queryType, recordStart, pageSize));
		return oneLine.toString();
	}
	
	public String produceMinTabDelOutputFromListWithHeading(
			List<RestEntityWrapper> list, String term, String props, String queryType, String recordStart, String pageSize)  {
//		CommonServices services = new CommonServices();

//		StringBuffer firstLine = new StringBuffer();
		String separator = "\t";
		StringBuffer oneLine = new StringBuffer();
		list.stream().forEach(x -> {
			oneLine.append(
				x.getParameters().getTerm());
			x.getConcepts().stream().forEach(y ->
				oneLine.append(separator + y.getCode() + 
				separator + y.getName()
				//separator + CommonServices.cleanListOutPut(CommonServices.getListValues(x.getParents())) +
				//services.fullyCuratedProperties(x.getSynonyms(), separator, CommonServices.SYNONYMS) + 
				//services.fullyCuratedProperties(x.getDefinitions(), separator, CommonServices.DEFINITIONS) + 
				//services.fullyCuratedProperties(x.getMaps(), separator, CommonServices.MAPS) + 
				//separator + services.calculateAndProduceSpacedTerms(separator))  
				));	
			oneLine.append("\r\n");});


			    //services.clearPropertyListsFromHeaderMap();});
		//services.filterHeadings(services).stream()
		//.forEach(x -> firstLine.append(x + separator));
		//String firstHeaderString = CommonServices.cleanListOutPut(firstLine.toString());
		//firstLine.replace(firstHeaderString.lastIndexOf(separator), firstHeaderString.length(), "");
		//String secondHeader = services.getHeadersByPosition(
//				services.getPropHeaderMap())
//						.stream()
//						.collect(Collectors.joining(separator));
		//oneLine.insert(0, firstHeaderString + secondHeader);
		oneLine.append(produceDelimitedQueryRecord(separator, term, props, queryType, recordStart, pageSize));
		return oneLine.toString();
	}
	
}
