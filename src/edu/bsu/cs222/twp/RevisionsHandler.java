package edu.bsu.cs222.twp;

import java.util.ArrayList;

public class RevisionsHandler {
	private ArrayList<Revision> revisions;
	private String titleAndRedirect;

	public void createNewSetOfRevisions(String pageName) {
		String output = WikipediaConnection.getXMLFileAsString(pageName);
		if (output.startsWith("There was an issue")) {
			sendOutErrorMessage(output);
		} else {
			createTitleAndRevisions(output);
		}
	}
	
	public void sendOutErrorMessage(String errorMessage){
		titleAndRedirect = errorMessage;
		revisions = new ArrayList<Revision>();		
	}
	
	public void createTitleAndRevisions(String output){
		titleAndRedirect = XMLParser.getRedirectAndTitle(output);
		revisions = XMLParser.createRevisionsFromXML(output);		
	}

	public String getPrintableStringOfRevisions(String sortType) {
		if (sortType.equalsIgnoreCase("Sort by date"))
			return titleAndRedirect + RevisionsSorter.sortByDate(revisions);
		else if (sortType.equalsIgnoreCase("Sort by # of edits"))
			return titleAndRedirect + RevisionsSorter.sortByNumberOfEdits(revisions);
		return "This string should never be returned.";
	}
}