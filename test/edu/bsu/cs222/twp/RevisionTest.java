package edu.bsu.cs222.twp;

import org.junit.Assert;
import org.junit.Test;

public class RevisionTest {
	
	@Test
	public void TestRevisionToString(){
		Revision testRevision = new Revision("a", "b", "c", "d");
		String testString = "Name: a\n\tDate: b\n\tTime: c\n\tComment: d\n";
		Assert.assertEquals(testRevision.toString(), testString);
	}
	
	@Test
	public void TestRevisionGetUsername(){
		Revision testRevision = new Revision("a", "b", "c", "d");
		Assert.assertEquals(testRevision.getUsername(), "a");
	}
}
