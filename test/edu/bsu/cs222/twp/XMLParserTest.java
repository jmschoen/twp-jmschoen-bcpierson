package edu.bsu.cs222.twp;

import java.util.ArrayList;

import org.junit.Test;

import org.junit.Assert;

public class XMLParserTest{
	
	private String sampleString ="><revisions><rev comment=\"\" timestamp=\"2015-09-20T07:03:24Z\" user=\"Stepbang\"";
	
	@Test
	public void textXMLParserSplitString(){
		String[] testArray = {"a","b"};
		String testString = "a<rev b";
		Assert.assertEquals(XMLParser.splitString(testString)[0], testArray[0]);
		Assert.assertEquals(XMLParser.splitString(testString)[1], testArray[1]);
	}
	
	@Test
	public void testXMLParser(){
		ArrayList<String> testArrayList = new ArrayList<String>();
		testArrayList.add("Stepbang");
		testArrayList.add("2015-09-20");
		testArrayList.add("07:03:24");
		testArrayList.add("");
		String[] testArray = {"", sampleString};
		Assert.assertEquals(XMLParser.trimStrings(testArray), testArrayList);
	}
	
	@Test
	public void testXMLParserCreateRevisions(){
		ArrayList<String> testArrayList = new ArrayList<String>();
		testArrayList.add("a");
		testArrayList.add("b");
		testArrayList.add("c");
		testArrayList.add("d");
		Revision testRevision = new Revision("a", "b", "c", "d");
		ArrayList<Revision> testRevisionArrayList = new ArrayList<Revision>();
		testRevisionArrayList.add(testRevision);
		Assert.assertEquals(XMLParser.defineRevisions(testArrayList).get(0).toString(), testRevisionArrayList.get(0).toString());
	}

}
