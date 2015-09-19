package edu.bsu.cs222.twp;

public class Initialization {
	public static void main(String[] args){
		XMLParser x = new XMLParser();
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
