package edu.bsu.cs222.twp;

import java.util.ArrayList;
import java.util.HashMap;

public class RevisionsSorter {
	
	private static BubbleSortHandler bubbleSortHandler = new BubbleSortHandler();
	
	public static String sortByDate(ArrayList<Revision> revisions){
		String output = "";
		for(Revision r: revisions){
			output += r.toString();
			}
		return output;
	}
	
	public static String sortByNumberOfEdits(ArrayList<Revision> revisions){
		HashMap<String, Integer> editors = createHashMapOfEditorsWithNumberOfEdits(revisions);
		String[] arrayOfEditors = bubbleSortHandler.bubbleSortHashMap(editors);
		return createStringOfSortedRevisions(editors, arrayOfEditors);
	}
	
	private static HashMap<String, Integer> createHashMapOfEditorsWithNumberOfEdits(ArrayList<Revision> revisions){
		HashMap<String, Integer> hashMapOfEditors = new HashMap<String, Integer>();
		for(Revision r: revisions){
			String username = r.getUsername();
			if(hashMapOfEditors.containsKey(username)){
				int numberOfRevisions = hashMapOfEditors.get(username);
				hashMapOfEditors.replace(username, numberOfRevisions+1);
			}
			else{
				hashMapOfEditors.put(username, 1);
			}
		}
		return hashMapOfEditors;
	}
	
	private static String createStringOfSortedRevisions(HashMap<String, Integer> editors, String[] arrayOfEditors){
		String editorsWithNumberOfRevisions = "";
		for(String username: arrayOfEditors){
			editorsWithNumberOfRevisions = editorsWithNumberOfRevisions + "Name: " + username + "\n\tNumber of Edits: " + editors.get(username) + "\n";
		}
		return editorsWithNumberOfRevisions;
	}
}
