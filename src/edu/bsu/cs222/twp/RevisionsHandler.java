package edu.bsu.cs222.twp;

import java.util.ArrayList;

public class RevisionsHandler {
	private ArrayList<Revision> revisions;
	
	public void createNewSetOfRevisions(String pageName){
		String output = WikipediaConnection.getXMLFileAsString(pageName);
		revisions = XMLParser.parseXML(output);
	}
	
	public String getPrintableStringOfRevisions(String sortType){
		if(sortType.equalsIgnoreCase("Date"))
			return RevisionsSorter.sortByDate(revisions);
		else if(sortType.equalsIgnoreCase("# of Edits"))
			return RevisionsSorter.sortByNumberOfEdits(revisions);
		return "This string should never be returned.";
	}
}