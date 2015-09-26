package edu.bsu.cs222.twp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UI extends Application {
	BorderPane pane = new BorderPane();
	private TextField inputField = new TextField();
	private TextArea outputField = new TextArea();
	private ScrollPane scrollPane = new ScrollPane();
	private Button searchButton = new Button("Search");
	final ComboBox<String> optionsComboBox = new ComboBox<String>();
	private RevisionsHandler revisionsHandler = new RevisionsHandler();

	public void start(Stage stage) throws Exception {
		outputField.setEditable(false);
		configure(stage);
	}

	private void configure(Stage stage) {
		stage.setTitle("Wikipedia Edits");
		configureSearchTopicButton();
		configureOptionsComboBox();
		stage.setScene(new Scene(createRoot()));
		stage.setWidth(500);
		stage.setHeight(800);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		outputField.setPrefHeight(stage.getHeight() - inputField.getHeight() - searchButton.getHeight());
		stage.show();
	}
	
	private void lockdownUI(boolean state){
		inputField.setEditable(!state);
		searchButton.setDisable(state);
		optionsComboBox.setDisable(state);
	}

	private Pane createRoot() {
		VBox root = new VBox();
		root.getChildren().addAll(inputField, searchButton, optionsComboBox, scrollPane);
		return root;
	}

	public void outputData(String data) {
		outputField.setText(data);
		scrollPane.setContent(outputField);
	}

	private void configureSearchTopicButton() {
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lockdownUI(true);
				revisionsHandler.createNewSetOfRevisions(inputField.getText());
				String outputOfRevisions = revisionsHandler
						.getPrintableStringOfRevisions(optionsComboBox.getSelectionModel().getSelectedItem());
				outputData(outputOfRevisions);
				lockdownUI(false);
			}
		});
	}

	private void configureOptionsComboBox(){
		 optionsComboBox.getItems().addAll("Sort by date","Sort by # of edits");
		 optionsComboBox.setValue("Sort by date");
	}
}
