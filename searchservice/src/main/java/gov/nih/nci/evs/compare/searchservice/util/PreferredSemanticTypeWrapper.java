package gov.nih.nci.evs.compare.searchservice.util;

public class PreferredSemanticTypeWrapper {
	
	
	String[] SemanticTypes = {
			"Intellectual Product",
			"Finding",
			"Pharmacologic Substance",
			"Neoplastic Process",
			"Gene or Genome",
			"Amino Acid, Peptide, or Protein",
			"Therapeutic or Preventive Procedure",
			"Disease or Syndrome",
			"Quantitative Concept",
			"Laboratory Procedure",
			"Geographic Area"};

	public String[] getSemanticTypes() {
		return SemanticTypes;
	}

	public void setSemanticTypes(String[] semanticTypes) {
		SemanticTypes = semanticTypes;
	}

}
