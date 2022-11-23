package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

public class JavaWriter extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				
		Controller controller = new Controller();
		
		View view = new View(controller);
		
		view.start(primaryStage);
	
	}
}