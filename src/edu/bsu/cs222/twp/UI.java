package edu.bsu.cs222.twp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

public class UI extends Application {
	// private static Group root = new Group();
	BorderPane pane = new BorderPane();
	// Scene scene = new Scene(pane, 500, 500);
	private Scene userInterface = new Scene(pane, 200, 150);
	private TextField inputField = new TextField();
	private TextArea outputField = new TextArea();
	private ScrollPane scrollPane = new ScrollPane();
	private Text text;
	private Stage stage = new Stage();
	private Button searchButton = new Button("Search");
	//ObservableList<String> options = FXCollections.observableArrayList("Sort by date", "Sort by # of edits");
	final ComboBox<String> optionsComboBox = new ComboBox<String>();

	private RevisionsHandler revisionsHandler = new RevisionsHandler();
	public static UI UI = null;
	public static final CountDownLatch latch = new CountDownLatch(1);//

	public UI() {
		setUI(this);
	}

	public void start(Stage stage) throws Exception {

		// BorderPane pane = new BorderPane();
		// Scene scene = new Scene(pane, 500, 500);
		// stage.setScene(scene);
		//
		// Label label = new Label("Hello");
		// pane.setCenter(label);
		//
		// stage.show();
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
		// optionsComboBox.setLayoutX(stage.getWidth()-optionsComboBox.getWidth());
		// optionsComboBox.setLayoutY(searchButton.getLayoutY());
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		outputField.setPrefHeight(stage.getHeight() - inputField.getHeight() - searchButton.getHeight());
		stage.show();
	}

	private Pane createRoot() {
		VBox root = new VBox();
		root.getChildren().addAll(inputField, searchButton, optionsComboBox, scrollPane);
		// root.getChildren().add(optionsComboBox);
		return root;
	}

	public void outputData(String data) {
		outputField.setText(data);
		scrollPane.setContent(outputField);
	}

	public static void setUI(UI UI0) {
		UI = UI0;
		latch.countDown();
	}

	private void configureSearchTopicButton() {
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				revisionsHandler.createNewSetOfRevisions(inputField.getText());
				String outputOfRevisions = revisionsHandler
						.getPrintableStringOfRevisions(optionsComboBox.getSelectionModel().getSelectedItem());
				outputData(outputOfRevisions);
			}
		});
	}

	private void configureOptionsComboBox(){
		 optionsComboBox.getItems().addAll("Sort by date","Sort by # of edits");
		 optionsComboBox.setValue("Sort by date");
	}
}
