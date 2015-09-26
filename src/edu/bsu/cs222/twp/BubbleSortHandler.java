package edu.bsu.cs222.twp;


import java.util.HashMap;
import java.util.Set;

public class BubbleSortHandler {
	
	private HashMap<String, Integer> originalHashMap;
	
	public String[] bubbleSortHashMap(HashMap<String, Integer> editors){
		originalHashMap = editors;
		Set<String> keySet = originalHashMap.keySet();
		String[] keySetArray = keySet.toArray(new String[keySet.size()]);
		
		for(int i = 0; i < keySetArray.length; i++){
			int marker = i;
			String tempString = keySetArray[i];
			for(int j = i; j < keySetArray.length; j++){
				String currentString = keySetArray[j];
				if(originalHashMap.get(tempString) < originalHashMap.get(currentString)){
					marker = j;
					tempString = currentString;
					
				}
			}
			String temp = keySetArray[i];
			keySetArray[i] = tempString;
			keySetArray[marker] = temp;
		}
		
		return keySetArray;
	}

}
