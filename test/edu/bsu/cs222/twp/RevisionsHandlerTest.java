package edu.bsu.cs222.twp;

import java.util.ArrayList;

import org.junit.Test;

import org.junit.Assert;

public class RevisionsHandlerTest {

	@Test
	public void testGetPrintableString(){
		ArrayList<Revision> testArrayList = new ArrayList<Revision>();
		RevisionsHandler revisionsHandler = new RevisionsHandler();
		testArrayList.add(new Revision("a", "b", "c", "d"));
		testArrayList.add(new Revision("e", "f", "g", "h"));
		testArrayList.add(new Revision("a", "j", "k", "l"));
		String testDateString = testArrayList.get(0).toString() + testArrayList.get(1).toString() + testArrayList.get(2).toString();
		String testEditsString = "Name: a\n\tNumber of Edits: 2\nName: i\n\tNumber of Edits: 1\n";
		Assert.assertEquals(revisionsHandler.getPrintableStringOfRevisions("sort by date"), testDateString);
		Assert.assertEquals(revisionsHandler.getPrintableStringOfRevisions("sort by # of edits"), testEditsString);
	}
}
