package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ProjectArea extends Area{
	
	public ProjectArea(Controller controller, Menus menuBar) {
		super(controller, menuBar);

		
		HBox leftbox = new HBox();
		leftbox.setSpacing(10);
		leftbox.setPadding(new Insets(200,200,10,240));
		
		Button newClassButton = new Button("New Class");
		newClassButton.setOnAction(e -> menuBar.newModelHandler());
		newClassButton.setPrefWidth(100);
		newClassButton.setPrefHeight(50);
		
		leftbox.getChildren().add(newClassButton);
		
		layout.setCenter(leftbox);
		
	}

}
