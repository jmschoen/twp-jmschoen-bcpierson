package edu.bsu.cs222.twp;

public class Initialization {
	public static void main(String[] args){
		Revision r = new Revision();
		XMLParser x = new XMLParser();
		System.out.println(r.getUsername());
		new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(UI.class);
            }
        }.start();
        UI userInterface = UI.waitForStartUpTest();
        x.parseXML(new String());
	}
}
