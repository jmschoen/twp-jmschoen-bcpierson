package edu.bsu.cs222.twp;

import java.util.HashMap;

import org.junit.Test;

import org.junit.Assert;

public class BubbleSortTest {
	BubbleSortHandler bubbleSortHandler = new BubbleSortHandler();
	
	@Test
	public void testBubbleSort(){
		HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("a", 3);
		testMap.put("b", 72);
		testMap.put("c", 5);
		String[] testArray = bubbleSortHandler.bubbleSortHashMap(testMap);
		String[] actualArray = {"b", "c", "a"};
		Assert.assertEquals(testArray[0], actualArray[0]);
		Assert.assertEquals(testArray[1], actualArray[1]);
		Assert.assertEquals(testArray[2], actualArray[2]);
	}

	@Test
	public void testCreateKeySetArray(){
		HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("a", 3);
		testMap.put("b", 72);
		testMap.put("c", 5);
		String[] testArray = bubbleSortHandler.createKeySetArray(testMap);
		Assert.assertEquals(testArray[0], "a");
		Assert.assertEquals(testArray[1], "b");
		Assert.assertEquals(testArray[2], "c");
	}
}
