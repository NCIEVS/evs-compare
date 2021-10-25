package gov.nih.nci.evs.compare.searchservice.util;

import java.util.List;

import gov.nih.nci.evs.compare.searchservice.model.RestEntityWrapper;

public class TabDelUtility extends FormatUtility {

	public String produceMinTabDelOutputFromListWithHeadingProps(List<RestEntityWrapper> list, String term, String props,
			String queryType, String recordStart, String pageSize) {
		String separator = "\t";
		StringBuffer oneLine = new StringBuffer();
		list.stream().forEach(x -> {
			oneLine.append(x.getParameters().getTerm());
			x.getConcepts().stream().forEach(y -> oneLine.append(separator + y.getCode() + separator + y.getName()));
			oneLine.append("\r\n");
		});
		oneLine.append(produceDelimitedQueryRecord(separator, term, props, queryType, recordStart, pageSize));
		return oneLine.toString();
	}

	public String produceMinTabDelOutputFromListWithHeading(List<RestEntityWrapper> list, String terms,
			String queryType, String recordStart, String pageSize) {
		String separator = "\t";
		StringBuffer oneLine = new StringBuffer();
		list.stream().forEach(x -> {
			oneLine.append(x.getParameters().getTerm());
			x.getConcepts().stream().forEach(y -> oneLine.append(separator + y.getCode() + separator + y.getName()));
			oneLine.append("\r\n");
		});
		oneLine.append(produceDelimitedQueryRecord(separator, terms, null, queryType, recordStart, pageSize));
		return oneLine.toString();
	}

}
