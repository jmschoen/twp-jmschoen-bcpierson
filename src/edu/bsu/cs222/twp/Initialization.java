package edu.bsu.cs222.twp;

public class Initialization {
	public static void main(String[] args){
		Revision r = new Revision();
		System.out.println(r.getUsername());
		
		
		new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(UI.class);
            }
        }.start();
        UI userInterface = UI.waitForStartUpTest();
       //.outputData();
        
       // UI userInterface = new UI();
		//UI.launch(args);
		//userInterface.outputData(r.getUsername());
	}
}
