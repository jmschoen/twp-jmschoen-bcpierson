package edu.bsu.cs222.twp;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WikipediaConnection {
	
	public static String getXMLFileAsString(String search){
		try{
		URLConnection connection = connectToWikipedia(createURL(search));
		Document document = readXmlDocumentFrom(connection);
		return transformXMLDocumentToString(document);
		}catch (IOException | ParserConfigurationException | SAXException | TransformerException exception){
			//This would be the only error
			String networkFailureString = "There was an issue connecting to the Wikipedia network.\n\tPlease try again later.";
			return networkFailureString;
		}
	}
	
	private static URL createURL(String search) throws MalformedURLException, UnsupportedEncodingException{
		String urlSearchInsert = URLEncoder.encode(search, "UTF-8");
		return new URL("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=xml&rvprop=timestamp%7Cuser%7Ccomment&rvlimit=30&titles=" + urlSearchInsert + "&redirects=");		
	}
	
	private static URLConnection connectToWikipedia(URL url) throws IOException {
		URLConnection connection = null;
			connection = url.openConnection();
		connection.setRequestProperty("User-Agent",
				"CS222TwoWeekProject/0.0.1 (http://www.cs.bsu.edu/homepages/pvg/courses/cs222Fa15/#!/two-week-project; bcpierson@bsu.edu)");
		connection.connect();
		return connection;
	}
	
	private static Document readXmlDocumentFrom(URLConnection connection) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return documentBuilder.parse(connection.getInputStream());
	}
	
	private static String transformXMLDocumentToString(Document doc) throws IOException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString().replaceAll("\n|\r", "");
	}
}
