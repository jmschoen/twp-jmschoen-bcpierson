package edu.bsu.cs222.twp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UI extends Application {
	private TextField inputField = new TextField();
	private TextArea outputField = new TextArea();
	private ScrollPane scrollPane = new ScrollPane();
	private Button searchButton = new Button("Search");
	private final ComboBox<String> optionsComboBox = new ComboBox<String>();
	private RevisionsHandler revisionsHandler = new RevisionsHandler();

	public void start(Stage stage) throws Exception {
		
		configure(stage);
	}

	private void configure(Stage stage) {
		configureStage(stage);
		configureSearchTopicButton();
		configureOptionsComboBox();
		configureInputField();
		configureOutputField(stage);
		configureScrollPane();
		stage.show();
	}
	
	private void configureSearchTopicButton() {
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (inputField.getText().equals("")) {
					printOutData("Please enter a search term!");
				} else {
					outputRequestedData();
				}
			}
		});
	}
	
	private void outputRequestedData(){
		lockdownUI(true);
		revisionsHandler.createNewSetOfRevisions(inputField.getText());
		String sortType = optionsComboBox.getSelectionModel().getSelectedItem();
		String stringOfRevisionData = revisionsHandler.getPrintableStringOfRevisions(sortType);
		printOutData(stringOfRevisionData);
		lockdownUI(false);
	}
	
	private void lockdownUI(boolean state) {
		inputField.setEditable(!state);
		searchButton.setDisable(state);
		optionsComboBox.setDisable(state);
	}
	
	public void printOutData(String data) {
		outputField.setText(data);
		scrollPane.setContent(outputField);
	}

	private void configureOptionsComboBox() {
		optionsComboBox.getItems().addAll("Sort by date", "Sort by # of edits");
		optionsComboBox.setValue("Sort by date");
	}
	
	private void configureInputField(){
		inputField.setText("Page Title");
	}
	
	private void configureOutputField(Stage stage){
		outputField.setEditable(false);
		outputField.setPrefHeight(stage.getHeight() - inputField.getHeight() - searchButton.getHeight());
	}
	
	private void configureScrollPane(){
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
	}
	
	private void configureStage(Stage stage){
		stage.setTitle("Wikipedia Edits");
		stage.setScene(new Scene(createRoot()));
		stage.setWidth(500);
		stage.setHeight(800);
	}
	
	private Pane createRoot() {
		VBox root = new VBox();
		root.getChildren().addAll(inputField, searchButton, optionsComboBox, scrollPane);
		return root;
	}
}
