package edu.bsu.cs222.twp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import java.util.concurrent.CountDownLatch;//


public class UI extends Application{
	//private static Group root = new Group();
	BorderPane pane = new BorderPane();
   // Scene scene = new Scene(pane, 500, 500);
	private Scene userInterface = new Scene(pane, 200, 150);
	private TextField inputField = new TextField();
    private TextField outputField = new TextField();
	private Text text;
	private Stage stage = new Stage();
	ObservableList<String> options = FXCollections.observableArrayList(
	        "one",
	        "two"
	    );
	final ComboBox optionsComboBox = new ComboBox(options);
	
	public static UI UI = null;
	 public static final CountDownLatch latch = new CountDownLatch(1);//
	
	public UI(){
		setUI(this);
	}
	
	public void start(Stage stage) throws Exception{
		
//		BorderPane pane = new BorderPane();
//        Scene scene = new Scene(pane, 500, 500);
//        stage.setScene(scene);
//
//        Label label = new Label("Hello");
//        pane.setCenter(label);
//
//        stage.show();
		outputField.setEditable(false);        
        configure(stage);
	}
	
	private void configure(Stage stage) {
    	stage.setTitle("Translator");
        stage.setScene(new Scene(createRoot()));
        stage.sizeToScene();
        stage.show();		
	}
	
	private Pane createRoot() {
    	VBox root = new VBox();
        root.getChildren().add(inputField);
        root.getChildren().add(new SearchTopicButton());
        root.getChildren().add(outputField);
        root.getChildren().add(optionsComboBox);
        return root;
	}
	
	public void outputData(String data){
		outputField.setText(data);
	}
	
	 public static void setUI(UI UI0) {
	        UI = UI0;
	        latch.countDown();
	    }
	
	 private class SearchTopicButton extends Button {
	    	//private PirateTranslator pirateTranslator = new PirateTranslator();
	    	//private IdentityTranslator identityTranslator = new IdentityTranslator();
	    	SearchTopicButton() {
	    		super("Search");
	    		setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//String selected = optionsComboBox.getSelectionModel().getSelectedItem());
						//String translation = pirateTranslator.translate(inputField.getText());
						//outputData(r.getUsername());						
					}
				});
	    	}
	    }
	 
	 
	 public static UI waitForStartUpTest() {
	        try {
	            latch.await();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return UI;
	    }
}
