package gov.nih.nci.evs.compare.searchservice.model;

import java.util.List;

public class Parameters {

	String term;
	String type;
	String include;
	String fromRecord;
	String pageSize;
	List<String> terminology;
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public String getFromRecord() {
		return fromRecord;
	}
	public void setFromRecord(String fromRecord) {
		this.fromRecord = fromRecord;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public List<String> getTerminology() {
		return terminology;
	}
	public void setTerminology(List<String> terminology) {
		this.terminology = terminology;
	}
}
