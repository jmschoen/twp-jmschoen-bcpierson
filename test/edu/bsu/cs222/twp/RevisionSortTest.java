package edu.bsu.cs222.twp;

import java.util.ArrayList;

import org.junit.Test;

import org.junit.Assert;

public class RevisionSortTest {
	
	@Test
	public void testSortByDate(){
		ArrayList<Revision> testArrayList = new ArrayList<Revision>();
		testArrayList.add(new Revision("a", "b", "c", "d"));
		testArrayList.add(new Revision("e", "f", "g", "h"));
		testArrayList.add(new Revision("i", "j", "k", "l"));
		String testString = testArrayList.get(0).toString() + testArrayList.get(1).toString() + testArrayList.get(2).toString();
		Assert.assertEquals(RevisionsSorter.sortByDate(testArrayList), testString);
	}
	
	@Test
	public void testSortByNumberOfEdits(){
		ArrayList<Revision> testArrayList = new ArrayList<Revision>();
		testArrayList.add(new Revision("a", "b", "c", "d"));
		testArrayList.add(new Revision("a", "f", "g", "h"));
		testArrayList.add(new Revision("i", "j", "k", "l"));
		String testString = "Name: a\n\tNumber of Edits: 2\nName: i\n\tNumber of Edits: 1\n";
		Assert.assertEquals(RevisionsSorter.sortByNumberOfEdits(testArrayList), testString);
	}

}
