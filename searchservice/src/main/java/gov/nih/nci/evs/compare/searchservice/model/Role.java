package gov.nih.nci.evs.compare.searchservice.model;

public class Role extends Rel {

	
	public String toString() {
		return "Role: " + getType() +  "Code: " + getRelatedCode() + " Name: " + getRelatedName();
	}

}
