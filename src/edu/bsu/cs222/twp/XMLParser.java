package edu.bsu.cs222.twp;

import java.util.ArrayList;

public class XMLParser {
	
	public static ArrayList<Revision> createRevisionsFromXML(String xmlFileString){
		String[] arrayOfRevisionDataStrings = splitString(xmlFileString);
		ArrayList<String> revisionData = trimStrings(arrayOfRevisionDataStrings);
		return defineRevisions(revisionData);
	}
	
	public static String[] splitString(String xmlFileString){
		return xmlFileString.split("<rev ");
	}
	
	public static ArrayList<String> trimStrings(String[] arrayOfRevisionData){
		ArrayList<String> revisionData = new ArrayList<String>();
		for (int i = 1; i < arrayOfRevisionData.length; i++){
			int userStart = arrayOfRevisionData[i].indexOf("user=")+6;
			int userEnd = arrayOfRevisionData[i].indexOf("\"", userStart);
			int dateStart = arrayOfRevisionData[i].indexOf("timestamp=")+11;
			int dateEnd = dateStart+10;
			int timeStart = dateEnd+1;
			int timeEnd = timeStart+8;
			int commentStart = arrayOfRevisionData[i].indexOf("comment=")+9;
			int commentEnd = arrayOfRevisionData[i].indexOf("\"", commentStart);
			revisionData.add(arrayOfRevisionData[i].substring(userStart, userEnd));
			revisionData.add(arrayOfRevisionData[i].substring(dateStart, dateEnd));
			revisionData.add(arrayOfRevisionData[i].substring(timeStart, timeEnd));
			revisionData.add(arrayOfRevisionData[i].substring(commentStart, commentEnd));
		}
		return revisionData;
	}
	
	public static String getRedirectAndTitle(String xmlFileData){
		String pageTitle = getTitleFromString(xmlFileData);
		String pageRedirect = getRedirectFromString(xmlFileData);
		return pageTitle + pageRedirect + "\n\n";
	}
	
	private static String getTitleFromString(String xmlFileData){
		int firstIndexOfTitle = xmlFileData.indexOf(" title=\"") + 8;
		int endIndexOfTitle = xmlFileData.indexOf("\"", firstIndexOfTitle);
		String pageTitle = "Page Title: " + xmlFileData.substring(firstIndexOfTitle, endIndexOfTitle);
		return pageTitle;
	}
	
	private static String getRedirectFromString(String xmlFileData){
		String startOfRedirectsString = "<normalized><n from=\"";
		if (xmlFileData.contains(startOfRedirectsString)){
			int firstIndexOfRedirect = xmlFileData.indexOf(startOfRedirectsString) + 21;
			int endIndexOfRedirect = xmlFileData.indexOf("\"", firstIndexOfRedirect);
			String pageRedirect = "\n\tRedirect from: " + xmlFileData.substring(firstIndexOfRedirect, endIndexOfRedirect);
			return pageRedirect;
		}
		else{
			return "";
		}
	}
	
	public static ArrayList<Revision> defineRevisions(ArrayList<String> revisionData){
		ArrayList<Revision> revisions = new ArrayList<Revision>();
		for (int i = 0; i < revisionData.size()/4; i++){
			String username = revisionData.get(i*4);
			String date = revisionData.get(i*4+1);
			String time = revisionData.get(i*4+2);
			String comment = revisionData.get(i*4+3);
			revisions.add(new Revision(username, date, time, comment));
		}
		return revisions;
	}
}
