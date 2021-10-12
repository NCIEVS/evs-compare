package gov.nih.nci.evs.compare.searchservice.model;

import java.util.List;

public class RestEntityWrapper {
	
	String total;
	String time;
	Parameters parameters;
	List<RestEntity> concepts;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	public List<RestEntity> getConcepts() {
		return concepts;
	}
	public void setConcepts(List<RestEntity> concepts) {
		this.concepts = concepts;
	}

}
