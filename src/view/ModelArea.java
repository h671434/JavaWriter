package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ModelArea extends Area{

	private TextField nameField;
	
	private ObservableList<TextField> variableList;
	private ListView<TextField> variableListView;
	
	private TextArea previewText;

	public ModelArea(Controller controller, Menus menuBar) {
		super(controller, menuBar);
		
		VBox editBox = new VBox();
		editBox.setSpacing(10);
		editBox.setPadding(new Insets(10, 10, 10, 10));

		Label nameLabel = new Label("Classname:");

		HBox editTopBox = new HBox();
		editTopBox.setSpacing(10);
		
		nameField = new TextField("Class" + String.format("%3d", (int) (1000 * Math.random())));
		nameField.setPrefColumnCount(19);
		
		nameField.textProperty().addListener((observable, oldValue, newValue) -> {
		   if(!menuBar.classExists(newValue)) {
			   controller.selectClass(oldValue);
			   controller.refactorClass(newValue);
			   menuBar.updateMenus(oldValue, newValue);
		   } else {
			  nameField.setText(newValue + "1");
		   }
		});
		
		if(menuBar.classExists(nameField.getText()))
			nameField.setText(nameField.getText() + "1");
		
		controller.newClass(nameField.getText());
		
		Button deleteClassButton = new Button("X");
		deleteClassButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	controller.selectClass(nameField.getText());
		    	menuBar.deleteModel(nameField.getText());
		    }
		});
		
		editTopBox.getChildren().add(nameField);
		editTopBox.getChildren().add(deleteClassButton);

		Label variableLabel = new Label("Variables:");

		variableList = FXCollections.observableArrayList();

		variableListView = new ListView<>(variableList);

		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		
		Button newVariableButton = new Button("Add variable");
		newVariableButton.setOnAction(e -> addVariable());

		Button deleteVariableButton = new Button("Delete Variable");
		deleteVariableButton.setOnAction(e -> variableList.remove(variableListView.getSelectionModel().getSelectedItem()));

		Button saveVariablesButton = new Button("Save");
		saveVariablesButton.setOnAction(e -> saveVariables());
		
		buttonBox.getChildren().add(newVariableButton);
		buttonBox.getChildren().add(deleteVariableButton);
		buttonBox.getChildren().add(saveVariablesButton);
		
		
		editBox.getChildren().add(nameLabel);
		editBox.getChildren().add(editTopBox);
		editBox.getChildren().add(variableLabel);
		editBox.getChildren().add(variableListView);
		editBox.getChildren().add(buttonBox);

		VBox previewBox = new VBox();
		previewBox.setSpacing(10);
		previewBox.setPadding(new Insets(100, 10, 10, 10));
		
		previewText = new TextArea();
		previewText.setPrefWidth(250);
		previewText.setPrefHeight(400);
		
		Button previewButton = new Button("Preview");
		previewButton.setOnAction(e -> previewClass());
		
		previewBox.getChildren().add(previewText);
		previewBox.getChildren().add(previewButton);
		
		HBox bottomBox = new HBox();

		layout.setLeft(editBox);
		layout.setRight(previewBox);
		layout.setBottom(bottomBox);

	}

	public TextField getNameField() {
		return nameField;
	}
	
	public void addVariable() {
		TextField variableField = new TextField("Datatype Name");
		variableField.setOnMouseClicked(e -> variableListView.getSelectionModel().select(variableField));
		
		variableList.add(variableField);
	}
	
	public void saveVariables() {
		controller.selectClass(nameField.getText());
		
		for (TextField field : variableList) {
			String[] var = field.getText().split(" ");
			controller.newVariable();
			controller.refactorVariable(var[0], var[1]);
		}
	}
	
	public void previewClass() {
		saveVariables();	
		previewText.setText(controller.getClassCode());
	}
}
