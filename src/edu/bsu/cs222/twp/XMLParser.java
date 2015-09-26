package edu.bsu.cs222.twp;

import java.util.ArrayList;

public class XMLParser {
	
	private static String sampleString ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><api><warnings><query xml:space=\"preserve\">Formatting of continuation data has changed. To receive raw query-continue data, use the 'rawcontinue' parameter. To silence this warning, pass an empty string for 'continue' in the initial query.</query></warnings><continue continue=\"||\" rvcontinue=\"20150814032335|676013828\"/><query><normalized><n from=\"peach\" to=\"Peach\"/></normalized><pages><page _idx=\"51257\" ns=\"0\" pageid=\"51257\" title=\"Peach\"><revisions><rev comment=\"\" timestamp=\"2015-09-20T07:03:24Z\" user=\"Stepbang\"/><rev comment=\"Reverted 1 edit by [[Special:Contributions/Renly Baratheon And His Peach|Renly Baratheon And His Peach]] ([[User talk:Renly Baratheon And His Peach|talk]]) to last revision by ClueBot NG. ([[WP:TW|TW]])\" timestamp=\"2015-09-19T14:53:24Z\" user=\"Mcmatter\"/><rev comment=\"A much needed edit.\" timestamp=\"2015-09-19T14:41:39Z\" user=\"Renly Baratheon And His Peach\"/><rev comment=\"Reverting possible vandalism by [[Special:Contribs/65.0.139.211|65.0.139.211]] to version by Look2See1. [[WP:CBFP|Report False Positive?]] Thanks, [[WP:CBNG|ClueBot NG]]. (2350488) (Bot)\" timestamp=\"2015-09-15T23:31:30Z\" user=\"ClueBot NG\"/><rev anon=\"\" comment=\"/* Description */\" timestamp=\"2015-09-15T23:31:28Z\" user=\"65.0.139.211\"/><rev anon=\"\" comment=\"/* Description */\" timestamp=\"2015-09-15T23:29:46Z\" user=\"65.0.139.211\"/><rev comment=\"/* External links */\" timestamp=\"2015-09-15T17:05:58Z\" user=\"Look2See1\"/><rev comment=\"/* External links */\" timestamp=\"2015-09-15T11:09:47Z\" user=\"Look2See1\"/><rev comment=\"/* Description */\" timestamp=\"2015-09-14T15:45:38Z\" user=\"Fanwen619\"/><rev comment=\"/* Description */\" timestamp=\"2015-09-12T15:24:10Z\" user=\"Fanwen619\"/><rev comment=\"Bot: Deprecating [[Template:Cite doi]] and some minor fixes\" timestamp=\"2015-09-04T06:19:42Z\" user=\"Dexbot\"/><rev anon=\"\" comment=\"/* Cultivars */\" timestamp=\"2015-09-03T22:47:52Z\" user=\"146.199.156.244\"/><rev comment=\"[[User:Monkbot/Task 7: CS1 et al. repair|Task 7c]]: repair/replace et al. in cs1 author/editor parameters;\" timestamp=\"2015-09-01T23:34:15Z\" user=\"Monkbot\"/><rev comment=\"quotation punctuation per MOS:QUOTEMARKS &amp; minor wording\" timestamp=\"2015-09-01T16:16:04Z\" user=\"TyrS\"/><rev comment=\"/* Production */ copyedit for table consistency; rv content not related to Production\" timestamp=\"2015-09-01T15:36:33Z\" user=\"Zefr\"/><rev comment=\"Reverted edits by [[Special:Contribs/67.45.96.52|67.45.96.52]] ([[User talk:67.45.96.52|talk]]) to last version by Dthomsen8\" timestamp=\"2015-09-01T04:55:51Z\" user=\"DavidLeighEllis\"/><rev anon=\"\" comment=\"/* Production */\" timestamp=\"2015-09-01T04:54:52Z\" user=\"67.45.96.52\"/><rev comment=\"/* Production */clean up, [[WP:AWB/T|typo(s) fixed]]: the the → the using [[Project:AWB|AWB]]\" timestamp=\"2015-08-31T06:45:51Z\" user=\"Dthomsen8\"/><rev comment=\"fixed [[Help:CS1_errors#bad_date|CS1 errors: dates]] to meet [[MOS:DATEFORMAT]] (also [[WP:AWB/GF|General fixes]]) using [[Project:AWB|AWB]] (11376)\" timestamp=\"2015-08-30T17:23:53Z\" user=\"BattyBot\"/><rev comment=\"Reverting possible vandalism by [[Special:Contribs/77.106.147.220|77.106.147.220]] to version by Dexbot. [[WP:CBFP|Report False Positive?]] Thanks, [[WP:CBNG|ClueBot NG]]. (2327929) (Bot)\" timestamp=\"2015-08-28T20:57:46Z\" user=\"ClueBot NG\"/><rev anon=\"\" comment=\"\" timestamp=\"2015-08-28T20:57:39Z\" user=\"77.106.147.220\"/><rev comment=\"Bot: Deprecating [[Template:Cite doi]] and some minor fixes\" timestamp=\"2015-08-28T15:07:02Z\" user=\"Dexbot\"/><rev comment=\"Rescuing 3 sources, flagging 0 as dead, and archiving 6 sources. ([[en:WP:PEACHY|Peachy 2.0 (alpha 8)]])\" timestamp=\"2015-08-28T09:19:32Z\" user=\"Cyberbot II\"/><rev comment=\"Bot: Deprecating [[Template:Cite pmid]] and some minor fixations\" timestamp=\"2015-08-27T16:41:54Z\" user=\"Dexbot\"/><rev comment=\"/* Cultivation */ Some material, with refs, about tree lifespan in the US\" timestamp=\"2015-08-25T22:53:26Z\" user=\"Novickas\"/><rev anon=\"\" comment=\"/* History */\" timestamp=\"2015-08-23T00:46:56Z\" user=\"186.18.183.221\"/><rev comment=\"range clarification + synonymy\" timestamp=\"2015-08-17T12:09:34Z\" user=\"Joseph Laferriere\"/><rev comment=\"/* Production */\" timestamp=\"2015-08-14T03:42:12Z\" user=\"Jimmyeatskids\"/><rev comment=\"/* Production */\" timestamp=\"2015-08-14T03:40:56Z\" user=\"Jimmyeatskids\"/><rev comment=\"/* Production */\" timestamp=\"2015-08-14T03:39:22Z\" user=\"Jimmyeatskids\"/></revisions></page></pages></query></api>";
	
	public static ArrayList<Revision> createRevisionsFromXML(String s){
		//String[] strings = splitString(s);
		String[] strings = splitString(sampleString);
		ArrayList<String> revisionData = trimStrings(strings);
		return defineRevisions(revisionData);
	}
	
	public static String[] splitString(String s){
		return s.split("<rev ");
	}
	
	public static ArrayList<String> trimStrings(String[] s){
		ArrayList<String> revisionData = new ArrayList<String>();
		for (int i = 1; i < s.length; i++){
			int userStart = s[i].indexOf("user=")+6;
			int userEnd = s[i].indexOf("\"", userStart);
			int dateStart = s[i].indexOf("timestamp=")+11;
			int dateEnd = dateStart+10;
			int timeStart = dateEnd+1;
			int timeEnd = timeStart+8;
			int commentStart = s[i].indexOf("comment=")+9;
			int commentEnd = s[i].indexOf("\"", commentStart);
			revisionData.add(s[i].substring(userStart, userEnd));
			revisionData.add(s[i].substring(dateStart, dateEnd));
			revisionData.add(s[i].substring(timeStart, timeEnd));
			revisionData.add(s[i].substring(commentStart, commentEnd));
		}
		return revisionData;
	}
	
	public static String getRedirectAndTitle(String filepath){
		//<normalized><n from=\"peach\" to=\"Peach\"/></normalized><pages><page _idx=\"51257\" ns=\"0\" pageid=\"51257\" title=\"Peach\"><revisions>
		int firstIndexOfTitle = filepath.indexOf(" title=\"") + 8;
		int endIndexOfTitle = filepath.indexOf("\"", firstIndexOfTitle);
		String pageTitle = filepath.substring(firstIndexOfTitle, endIndexOfTitle);
		if (filepath.contains("<normalized><n from=\"")){
			int firstIndexOfRedirect = filepath.indexOf("<normalized><n from=\"") + 21;
			int endIndexOfRedirect = filepath.indexOf("\"", firstIndexOfRedirect);
			String pageRedirect = filepath.substring(firstIndexOfRedirect, endIndexOfRedirect);
			String titleAndRedirect = "Page Title: " + pageTitle + "\n\tRedirect from: " + pageRedirect + "\n\n";
			return titleAndRedirect;
		}
		String titleOnly = "Page Title: " + pageTitle + "\n\n";
		return titleOnly;
	}
	
	public static ArrayList<Revision> defineRevisions(ArrayList<String> revisionData){
		ArrayList<Revision> revisions = new ArrayList<Revision>();
		for (int i = 0; i < revisionData.size()/4; i++){
			String username = revisionData.get(i*4);
			String date = revisionData.get(i*4+1);
			String time = revisionData.get(i*4+2);
			String comment = revisionData.get(i*4+3);
			revisions.add(new Revision(username, date, time, comment));
		}
		return revisions;
	}
}
