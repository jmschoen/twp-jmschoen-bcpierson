package edu.bsu.cs222.twp;

public class Revision {
	
	private String username;
	private String date;
	private String time;
	private String comment;
	
	public Revision(String inputUsername, String inputDate, String inputTime, String inputComment){
		username = inputUsername;
		date = inputDate;
		time = inputTime;
		comment = inputComment;
	}
	
	@Override
	public String toString(){
		return "Name: " + username +
				"\n\tDate: " + date +
				"\n\tTime: " + time +
				"\n\tComment: " + comment +"\n";
	}
	
	public String getUsername(){
		return username;
	}
}
