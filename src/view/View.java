package view;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
	
	Stage window;
	ProjectArea projectScene;
	
	private Controller controller = null;	

	public View(Controller controller) {
		this.controller = controller;
	}
	
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Java Writer v2.0");
		
		Menus menuBar = new Menus(controller, window);
		
		projectScene = new ProjectArea(controller, menuBar);	
		window.setScene(projectScene.getArea());
		
		window.show();
	}
}