package gov.nih.nci.evs.compare.searchservice.model;

public class Association extends Rel {

	
	public String toString() {
		return "Association: " + getType() +  "Code: " + getRelatedCode() + " Name: " + getRelatedName();
	}

}
