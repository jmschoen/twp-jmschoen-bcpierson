package edu.bsu.cs222.twp;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class RevisionsHandler {
	private ArrayList<Revision> revisions;
	
	public void createNewSetOfRevisions(String pageName){
		String output = WikipediaConnection.getXMLFileAsString(pageName);
		revisions = XMLParser.parseXML(output);
	}
	
	public String getPrintableStringOfRevisions(){
		String s = "";
		for(Revision r: revisions){
			s += r.toString();
			}
		return s;
	}
}
