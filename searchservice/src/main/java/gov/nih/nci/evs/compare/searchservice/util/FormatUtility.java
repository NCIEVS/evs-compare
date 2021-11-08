package gov.nih.nci.evs.compare.searchservice.util;

public class FormatUtility {
	
	public final static String[] FIELDS = {"terminology", "code", "name", "parents", "synonyms", "definitions", "Maps_To"};
	public final static String[] ROLE_FIELDS = {"concept code", "concept name", "role","target code","target name"};
	public final static String[] ASSOCIATION_FIELDS = {"concept code", "concept name", "association","target code","target name"};

	
	public String produceDelimitedQueryRecord(
			String separator, String term, String props, String queryType, String recordStart, String pageSize) {
		String appendedQuery = "\r\n" + "\r\n" + "\r\n" +
				"\r\n" + "Search parameters: " + 
				"\r\n" +  "\"|Input:  " + term + "|\"" +
				"\r\n" +  "\"|Query Type:  " + queryType + "|\"" +
				"\r\n" +  "\"|From Record:  " + recordStart + "|\"" +
				"\r\n" +  "\"|Concept attributes:  " + props + "|\"" +
				"\r\n" +  "\"|Page size:  " + pageSize + "|\"";
		return appendedQuery;
	}
	
	public String produceDelimitedRoleQueryRecord(String separator, String codes, String props) {
		String appendedQuery = "\r\n" + "\r\n" + "\r\n" +
				"\r\n" + "Report Search Parameters: " + 
				"\r\n" +  "\"|Input:  " + codes + "|\"" +
				"\r\n" +  "\"|Roles Selected: " + props + "|\"";
		return appendedQuery;
	}
	
	
	public String produceDelimitedAssocQueryRecord(String separator, String codes, String props) {
		String appendedQuery = "\r\n" + "\r\n" + "\r\n" +
				"\r\n" + "Report Search Parameters: " + 
				"\r\n" +  "\"|Input:  " + codes + "|\"" +
				"\r\n" +  "\"|Associations Selected: " + props + "|\"";
		return appendedQuery;
	}
}
