package view;

import java.io.File;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public abstract class Area {

	private static String CSSFILE = "stylesheet.css";

	protected Scene area;

	protected Controller controller;
	protected Menus navBar;

	protected BorderPane layout;

	public Area(Controller controller, Menus navBar) {
		this.controller = controller;
		this.navBar = navBar;

		layout = new BorderPane();
		
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.setPadding(new Insets(10,10,10,10));
		
		Button directoryButton = new Button("Choose directory");
		directoryButton.setOnAction(e -> chooseDirectory());
		
		Button writeClassButton = new Button("Write class");
		writeClassButton.setOnAction(e -> controller.writeClass());
		
		Button writeProjectButton = new Button("Write project");
		writeProjectButton.setOnAction(e -> controller.writeProject());
		
		buttonBox.getChildren().add(directoryButton);
		buttonBox.getChildren().add(writeClassButton);
		buttonBox.getChildren().add(writeProjectButton);
		
		layout.setTop(navBar.getMenuBar());
		layout.setBottom(buttonBox);

		area = new Scene(layout, 555, 555);
		area.getStylesheets().add(getClass().getResource(CSSFILE).toExternalForm());
	}

	public Scene getArea() {
		layout.setTop(navBar.getMenuBar());
		return area;
	}
	
	public void chooseDirectory() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Java Project Directory");
		
		File directory = new File("C:\\Users\\Aleksander XXVI\\eclipse-workspace");
		chooser.setInitialDirectory(directory);
		
		directory = chooser.showDialog(new Stage());
		
		controller.SelectDir(directory);
	}

}
