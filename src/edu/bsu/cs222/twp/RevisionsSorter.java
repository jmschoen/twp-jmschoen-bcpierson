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
	//new name?
	private static HashMap<String, Integer> createHashMapOfEditorsWithNumberOfEdits(ArrayList<Revision> revisions){
		HashMap<String, Integer> editors = new HashMap<String, Integer>(); //rename editors
		for(Revision r: revisions){
			String name = r.getUsername(); //
			if(editors.containsKey(name)){
				editors.replace(name, editors.get(name)+1);
			}
			else{
				editors.put(name, 1);
			}
		}
		return editors;
	}
	
	private static String createStringOfSortedRevisions(HashMap<String, Integer> editors, String[] arrayOfEditors){
		String output = "";
		for(String name: arrayOfEditors){
			output = output + "Name: " + name + "\n\tNumber of Edits: " + editors.get(name) + "\n";
		}
		return output;
	}
}
